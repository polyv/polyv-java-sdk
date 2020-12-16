package net.polyv.common.v1.base;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.util.SDKValidateUtil;
import net.polyv.common.v1.validator.ViolationMsg;
import net.polyv.common.v1.validator.constraints.Length;
import net.polyv.common.v1.validator.constraints.Max;
import net.polyv.common.v1.validator.constraints.Min;
import net.polyv.common.v1.validator.constraints.NotBlank;
import net.polyv.common.v1.validator.constraints.NotEmpty;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.common.v1.validator.handle.Validator;

/**
 * 测试自定义的SDK参数验证器
 * @author: sadboy
 **/
@Slf4j
public class SDKValidatorTest {
    private static final Validator VALIDATOR = Validator.getValidator().setFastFail(false);
    private static final Validator VALIDATOR_NOTNULL = Validator.getValidator(NotNull.class).setFastFail(false);
    private static final Validator VALIDATOR_NOT_EMPTY = Validator.getValidator(NotEmpty.class).setFastFail(false);
    private static final Validator VALIDATOR_NOT_BLANK = Validator.getValidator(NotBlank.class).setFastFail(false);
    private static final Validator VALIDATOR_MAX = Validator.getValidator(Max.class).setFastFail(false);
    private static final Validator VALIDATOR_MIN = Validator.getValidator(Min.class).setFastFail(false);
    private static final Validator VALIDATOR_LENGTH = Validator.getValidator(Length.class).setFastFail(false);
    private static final String ALL_INFO = "不能为null / year不允许为空 / name不能为empty / name不能为null / file不能为null / " +
            "englishName不能为empty / englishName不能为blank / password不能为empty / password不能为blank / description不能为blank / " +
            "description1不能为blank / diy不允许为空 / birthday不允许为空 / relationship不允许为空 / info不允许为空 / 身高height不允许为空";
    private static final String NOT_NULL_INFO =
            "不能为null / year不允许为空 / file不能为null / diy不允许为空 / birthday不允许为空 / relationship不允许为空 /" +
                    " info不允许为空 / 身高height不允许为空";
    
    @Test
    public void testAllValidator() {
        Student student = new Student();
        List<ViolationMsg> validate = VALIDATOR.validate(student);
        if (!validate.isEmpty()) {
            String violationMsgStr = SDKValidateUtil.getViolationMsgStr(validate);
            String substring = violationMsgStr.substring(0, violationMsgStr.length() - 3);
            log.error("Student验证出错：" + substring);
            Assert.assertEquals(ALL_INFO, substring);
        }
    }
    
    @Test
    public void testNotNullValidator() {
        Student student = new Student();
        student.setName("");
        student.setEnglishName(" ");
        student.setPassword("123");
        List<ViolationMsg> validate = VALIDATOR_NOTNULL.validate(student);
        Assert.assertTrue(!validate.isEmpty());
        String violationMsgStr = SDKValidateUtil.getViolationMsgStr(validate);
        String substring = violationMsgStr.substring(0, violationMsgStr.length() - 3);
        log.info("Student验证出错：" + substring);
        Assert.assertEquals(NOT_NULL_INFO, substring);
    }
    
    @Test
    public void testNotEmptyValidator() {
        Student student = new Student();
        student.setName("");
        student.setEnglishName(" ");
        student.setPassword("123");
        List<ViolationMsg> validate = VALIDATOR_NOT_EMPTY.validate(student);
        Assert.assertTrue(!validate.isEmpty());
        String violationMsgStr = SDKValidateUtil.getViolationMsgStr(validate);
        String substring = violationMsgStr.substring(0, violationMsgStr.length() - 3);
        log.info("Student验证出错：" + substring);
        Assert.assertEquals("name不能为empty", substring);
    }
    
    @Test
    public void testNotBlankValidator() {
        Student student = new Student();
        student.setEnglishName(" ");
        student.setPassword("");
        student.setDescription(null);
        student.setDescription1("121");
        List<ViolationMsg> validate = VALIDATOR_NOT_BLANK.validate(student);
        Assert.assertTrue(!validate.isEmpty());
        String violationMsgStr = SDKValidateUtil.getViolationMsgStr(validate);
        String substring = violationMsgStr.substring(0, violationMsgStr.length() - 3);
        log.info("Student验证出错：" + substring);
        Assert.assertEquals("englishName不能为blank / password不能为blank / description不能为blank", substring);
    }
    
    @Test
    public void testMaxValidator() {
        Student student = new Student();
        student.setIncome(30l);
        student.setPay(20.5f);
        List<ViolationMsg> validate = VALIDATOR_MAX.validate(student);
        Assert.assertTrue(!validate.isEmpty());
        String violationMsgStr = SDKValidateUtil.getViolationMsgStr(validate);
        String substring = violationMsgStr.substring(0, violationMsgStr.length() - 3);
        log.info("Student验证出错：" + substring);
        Assert.assertEquals("income超出最大值20 / pay超出最大值20", substring);
    }
    
    @Test
    public void testMinValidator() {
        Student student = new Student();
        student.setIncome(29l);
        student.setPay(29.9f);
        List<ViolationMsg> validate = VALIDATOR_MIN.validate(student);
        Assert.assertTrue(!validate.isEmpty());
        String violationMsgStr = SDKValidateUtil.getViolationMsgStr(validate);
        String substring = violationMsgStr.substring(0, violationMsgStr.length() - 3);
        log.info("Student验证出错：" + substring);
        Assert.assertEquals("income小于最小值30 / pay小于最小值30", substring);
    }
    
    @Test
    public void testLengthValidator() {
        Student student = new Student();
        student.setPassword("1200");
        List<ViolationMsg> validate = VALIDATOR_LENGTH.validate(student);
        Assert.assertTrue(!validate.isEmpty());
        String violationMsgStr = SDKValidateUtil.getViolationMsgStr(validate);
        String substring = violationMsgStr.substring(0, violationMsgStr.length() - 3);
        log.info("Student验证出错：" + substring);
        Assert.assertEquals("密码长度必须8到16位", substring);
        
        student = new Student();
        student.setPassword("idsaffdadadsa2983209u121dsoi");
        validate = VALIDATOR_LENGTH.validate(student);
        Assert.assertTrue(!validate.isEmpty());
        violationMsgStr = SDKValidateUtil.getViolationMsgStr(validate);
        substring = violationMsgStr.substring(0, violationMsgStr.length() - 3);
        log.info("Student验证出错：" + substring);
        Assert.assertEquals("密码长度必须8到16位", substring);
        
        student = new Student();
        student.setPassword("1200455657A");
        validate = VALIDATOR_LENGTH.validate(student);
        Assert.assertTrue(validate.isEmpty());
    }
    
    @Test
    public void testNullData() {
        MoneyStudent moneyStudent = new MoneyStudent();
        List<ViolationMsg> validate = VALIDATOR.validate(moneyStudent);
        log.info(JSON.toJSONString(validate));
        Assert.assertTrue(validate.isEmpty());
    }
    
    @Test(expected = NullPointerException.class)
    public void testNullExData() {
        ((String) null).toLowerCase();
    }
    
    @Data
    public class Student extends Person {
        @NotNull(message = "id不允许为空")
        private int id;
        
        @NotNull
        private Integer age;
        
        @NotNull(message = "year不允许为空")
        private Integer year;
        
        @NotEmpty(message = "name不能为empty")
        @NotNull(message = "name不能为null")
        private String name;
        
        @NotNull(message = "file不能为null")
        private File file;
        
        @NotEmpty(message = "englishName不能为empty")
        @NotBlank(message = "englishName不能为blank")
        private String englishName;
        
        @NotEmpty(message = "password不能为empty")
        @NotBlank(message = "password不能为blank")
        @Length(min = 8, max = 16, message = "密码长度必须8到16位")
        private String password;
        
        @NotBlank(message = "description不能为blank")
        private String description;
        
        @NotBlank(message = "description1不能为blank")
        private String description1;
        
        @NotNull(message = "diy不允许为空")
        private StringBuffer diy;
        
        @NotNull(message = "birthday不允许为空")
        private Date birthday;
        
        @NotNull(message = "relationship不允许为空")
        private List<String> relationship;
        
        @NotNull(message = "info不允许为空")
        private Map<String, String> info;
        
        @Max(value = Integer.MAX_VALUE)
        private Long money;
        
        @Max(value = 20, message = "income超出最大值20")
        @Min(value = 30, message = "income小于最小值30")
        private Long income;
        
        @Max(value = 20, message = "pay超出最大值20")
        @Min(value = 30, message = "pay小于最小值30")
        private Float pay;
    }
    
    @Data
    public class MoneyStudent {
        
        @Max(value = Integer.MAX_VALUE)
        private Long money;
        
        @Max(value = Integer.MAX_VALUE)
        private Long price;
        
    }
    
    @Data
    public class Person {
        @NotNull(message = "身高height不允许为空")
        private Integer height;
        @NotNull(message = "体重weight不允许为空")
        private int weight;
    }
    
}
