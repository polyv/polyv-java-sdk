package net.polyv.common.v1.validator.handle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.validator.ViolationMsg;

@Slf4j
public abstract class Validator {
    
    protected Validator nextLiveRequestValidator;
    
    protected Class currentClass;
    
    private boolean fastFail = false;
    
    public Validator setFastFail(boolean fastFail) {
        this.fastFail = fastFail;
        return this;
    }
    
    public void setNextLiveRequestValidator(Validator nextLiveRequestValidator) {
        this.nextLiveRequestValidator = nextLiveRequestValidator;
    }
    
    public final List<ViolationMsg> validate(Object data, Class<?>... groups) {
        try {
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
    
    protected final List<ViolationMsg> validate(Annotation annotation, Field field, Object data, Class<?>... groups) {
        List<ViolationMsg> violationMsgList = new ArrayList<ViolationMsg>();
        if (null != currentClass && currentClass.equals(annotation.annotationType())) {
            String msg = dealValidate(annotation, data, groups);
            if (msg != null) {
                ViolationMsg violationMsg = new ViolationMsg();
                violationMsg.setMsg(msg).setFieldData(data).setField(field);
                violationMsgList.add(violationMsg);
            }
            return violationMsgList;
        }
        if (null != nextLiveRequestValidator) {
            violationMsgList.addAll(nextLiveRequestValidator.validate(annotation, field, data, groups));
        }
        return violationMsgList;
    }
    
    abstract protected String dealValidate(Annotation annotation, Object data, Class<?>... groups);
    
    
    public static Validator getValidator() {
        NotNullValidator liveRequestNullValidator = new NotNullValidator();
        liveRequestNullValidator.setNextLiveRequestValidator(new NotEmptyValidator());
        return liveRequestNullValidator;
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
