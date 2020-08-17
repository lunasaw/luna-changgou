package wxsdk;

import com.github.wxpay.sdk.WXPayUtil;
import org.junit.Test;

/**
 * @Package: wxsdk
 * @ClassName: WxSdkTest
 * @Author: luna
 * @CreateTime: 2020/8/16 13:32
 * @Description:
 */
public class WxSdkTest {
    @Test
    public void atest() {
        String s = WXPayUtil.generateNonceStr();
        System.out.println(s);
    }
}
