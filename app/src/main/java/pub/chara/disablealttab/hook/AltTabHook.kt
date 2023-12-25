package pub.chara.disablealttab.hook

import android.view.KeyEvent
import com.github.kyuubiran.ezxhelper.utils.*
import de.robv.android.xposed.XposedBridge

object AltTabHook : BaseHook() {
    override fun init() {
        try {
            //disable hot-key
            //this works for any android version
            findMethod("com.android.server.policy.PhoneWindowManager") {
                name == "interceptKeyBeforeDispatching"
            }.hookBefore { param ->
                run {
                    param.result = 0L;
                }
            }
            XposedBridge.log("DisableAltTab: AltTabHook success!")
        } catch (e: Throwable) {
            XposedBridge.log("DisableAltTab: AltTabHook failed!")
            XposedBridge.log(e)
        }
    }
}
