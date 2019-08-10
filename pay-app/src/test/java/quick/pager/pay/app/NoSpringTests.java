package quick.pager.pay.app;

import cn.hutool.core.util.ReUtil;
import org.junit.Test;
import quick.pager.pay.Constants;

public class NoSpringTests {


    @Test
    public void testReg() {

        System.out.println(ReUtil.contains(Constants.Reg.ONLY_NUMBERS_AND_LETTERS, "ddddAKdiodld?127fdffsfsfsfsfererkk001"));
    }
}
