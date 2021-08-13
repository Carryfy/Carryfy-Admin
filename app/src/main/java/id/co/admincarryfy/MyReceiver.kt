package id.co.admincarryfy

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.widget.Toast
import androidx.annotation.RequiresApi

class MyReceiver : BroadcastReceiver() {
    companion object{
        const val SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"
        const val TAG = "Receive SMS"
        val pdu_type = "pdus"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action == SMS_RECEIVED){
            val bundle = intent.extras
            val msgs: Array<SmsMessage?>
            var strMessage = ""
            val format = bundle!!.getString("format")
            // Retrieve the SMS message received.
            val pdus = bundle[pdu_type] as Array<Any>?
            if (pdus != null) { // Check the Android version.
                val isVersionM = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                msgs = arrayOfNulls(pdus.size)
                for (i in msgs.indices) { // Check Android version and use appropriate createFromPdu.
                    if (isVersionM) { // If Android version M or newer:
                        msgs[i] = SmsMessage.createFromPdu(
                                pdus[i] as ByteArray,
                                format
                        )
                    } else { // If Android version L or older:
                        msgs[i] =
                                SmsMessage.createFromPdu(pdus[i] as ByteArray)
                    }
                    // Build the message to show.
                    strMessage += "SMS from " + msgs[i]?.originatingAddress
                    strMessage += " :" + (msgs[i]?.messageBody) + "\n"
                    // Log and display the SMS message.

                }
                Toast.makeText(context, "Mesage: ${strMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}