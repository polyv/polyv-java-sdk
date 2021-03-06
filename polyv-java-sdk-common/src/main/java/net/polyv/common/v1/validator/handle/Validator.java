package net.polyv.common.v1.validator.handle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.common.v1.validator.ViolationMsg;

/**
 * 自定义验证器父类
 */
@Slf4j
public abstract class Validator {
    
    /**
     * 下一个验证器，为null则为最后一个验证器
     */
    private Validator nextRequestValidator;
    
    /**
     * 验证器对应注解的class
     */
    protected Class currentClass;
    
    /**
     * 是否快速验证标识，true为快速验证，false为全部验证后返回
     */
    private boolean fastFail = false;
    
    public Class getCurrentClass() {
        return currentClass;
    }
    
    /**
     * 是否快速验证(一个验证不通过即返回)，true为快速验证，false为所有验证结束后统一返回，默认为false
     * @param fastFail 快速验证
     * @return
     */
    public Validator setFastFail(boolean fastFail) {
        this.fastFail = fastFail;
        return this;
    }
    
    /**
     * 设置链式验证器下一个节点
     * @param nextRequestValidator
     */
    public void setNextRequestValidator(Validator nextRequestValidator) {
        this.nextRequestValidator = nextRequestValidator;
    }
    
    /**
     * 验证data数据是否满足数据要求
     * @param data 数据
     * @param groups 需要验证的分组
     * @return
     */
    public final List<ViolationMsg> validate(Object data, Class<?>... groups) {
        try {
            groups = groups == null ? new Class[]{} : groups;
            Class<?> objClass = data.getClass();
            List<ViolationMsg> violationMsgList = new ArrayList<ViolationMsg>();
            while (null != objClass) {
                Field[] fields = objClass.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Field f = objClass.getDeclaredField(fields[i].getName());
                    f.setAccessible(true);
                    Object o = f.get(data);
                    Annotation[] annotations = f.getAnnotations();
                    for (Annotation annotation : annotations) {
                        violationMsgList.addAll(validate(annotation, f, o, groups));
                        if (fastFail && !violationMsgList.isEmpty()) {
                            break;
                        }
                    }
                }
                objClass = objClass.getSuperclass();
            }
            return violationMsgList;
        } catch (NoSuchFieldException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        } catch (SecurityException e) {
            log.error(e.getMessage(), e);
        }
        return new ArrayList<ViolationMsg>();
    }
    
    /**
     * 链式验证，如果不属于当前验证器，调用nextRequestValidator去验证
     * @param annotation 注解对象
     * @param field 验证的字段
     * @param data 字段所对应的数据
     * @param groups 需要验证的分组，不能为空
     * @return
     */
    private final List<ViolationMsg> validate(Annotation annotation, Field field, Object data, Class<?>... groups) {
        List<ViolationMsg> violationMsgList = new ArrayList<ViolationMsg>();
        if (null != currentClass && currentClass.equals(annotation.annotationType())) {
            String msg = dealValidate(annotation, field, data, groups);
            if (msg != null) {
                ViolationMsg violationMsg = new ViolationMsg();
                violationMsg.setMsg(msg).setFieldData(data).setField(field);
                violationMsgList.add(violationMsg);
            }
            return violationMsgList;
        }
        if (null != nextRequestValidator) {
            violationMsgList.addAll(nextRequestValidator.validate(annotation, field, data, groups));
        }
        return violationMsgList;
    }
    
    /**
     * 每一个验证器需要处理的逻辑
     * @param annotation 注解对象
     * @param data 字段对应的数据
     * @param groups 需要验证的组，验证所有组不传该参数即可
     * @return
     */
    abstract protected String dealValidate(Annotation annotation, Field field, Object data, Class<?>... groups);
    
    /**
     * 获取验证器链表
     * @return 返回验证器链表第一个元素
     */
    public static Validator getValidator() {
        return getValidator(null);
    }
    
    /**
     * 根据注解class获取验证器
     * @param clazz
     * @return
     */
    public static Validator getValidator(Class... clazz) {
        Validator validator;
        if (clazz != null) {
            Map<Class, Validator> validatorMap = getValidatorMap();
            List<Validator> validatorList = new ArrayList<Validator>();
            for (Class temp : clazz) {
                Validator tempValidator = validatorMap.get(temp);
                if (tempValidator != null) {
                    validatorList.add(tempValidator);
                }
            }
            validator = initValidator(validatorList);
        } else {
            validator = initValidator(getValidatorList());
        }
        if (validator == null) {
            throw new PloyvSdkException(500, "请设置正确的注解class");
        }
        return validator;
    }
    
    private static Validator initValidator(List<Validator> validatorList) {
        Validator baseValidator = null;
        for (Validator temp : validatorList) {
            if (baseValidator == null) {
                baseValidator = temp;
            } else if (temp != null) {
                temp.setNextRequestValidator(baseValidator);
                baseValidator = temp;
            }
        }
        return baseValidator;
    }
    
    /**
     * 获取验证器map
     * @return
     */
    private static Map<Class, Validator> getValidatorMap() {
        Map<Class, Validator> validatorMap = new HashMap<Class, Validator>();
        for (Validator validator : getValidatorList()) {
            validatorMap.put(validator.getCurrentClass(), validator);
        }
        LengthValidator lengthValidator = new LengthValidator();
        validatorMap.put(lengthValidator.getCurrentClass(), lengthValidator);
        return validatorMap;
    }
    
    /**
     * 获取验证器列表，具体验证会从后往前进行查询
     * @return
     */
    private static List<Validator> getValidatorList() {
        List list = Arrays.asList(new LengthValidator(), new MinValidator(), new MaxValidator(),
                new NotBlankValidator(), new NotEmptyValidator(), new NotNullValidator());
        return list;
    }
    
    /**
     * 判断groupsAnnotate是否满足groups里面的class，满足返回true，反之返回false
     * @param groups 需要对内容进行校验的groups,禁止为null
     * @param groupsAnnotate 注解上的groups,禁止为null
     * @return
     */
    protected boolean showMsg(Class<?>[] groups, Class<?>[] groupsAnnotate) {
        if (groups.length > 0) {
            if (groupsAnnotate.length > 0) {
                return hasSame(groups, groupsAnnotate);
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
    
    /**
     * 判断groupsOne和groupsTwo是否存在相同元素，存在返回true，反之返回false
     * @param groupsOne 非空数组1
     * @param groupsTwo 非空数组2
     * @return
     */
    private boolean hasSame(Class<?>[] groupsOne, Class<?>[] groupsTwo) {
        HashSet<Class<?>> set = new HashSet<Class<?>>(Arrays.asList(groupsOne));
        set.retainAll(Arrays.asList(groupsTwo));
        return set.size() > 0;
    }
    
}
