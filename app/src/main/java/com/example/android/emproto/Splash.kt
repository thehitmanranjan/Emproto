package com.example.android.emproto

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.widget.Toast

class Splash : AppCompatActivity() {

    var permissions=arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.MODIFY_AUDIO_SETTINGS,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.PROCESS_OUTGOING_CALLS,
        Manifest.permission.RECORD_AUDIO
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if(permissionGiven(this,*permissions))
        {
            Handler().postDelayed({
                val i= Intent(this,MainActivity::class.java)
                startActivity(i)
                this.finish()
            },1000)
        }
        else
        {
            ActivityCompat.requestPermissions(this,permissions,131)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode)
        {
            131->{
                if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED
                    &&grantResults[1]==PackageManager.PERMISSION_GRANTED
                    &&grantResults[2]==PackageManager.PERMISSION_GRANTED
                    &&grantResults[3]==PackageManager.PERMISSION_GRANTED
                    &&grantResults[4]== PackageManager.PERMISSION_GRANTED){
                    Handler().postDelayed({
                        val i= Intent(this,MainActivity::class.java)
                        startActivity(i)
                        this.finish()
                    },1000)
                }
                else{
                    Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                    this.finish()
                }
            }
            else->{
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                this.finish()
            }
        }
    }
    fun permissionGiven(context: Context, vararg permissions:String):Boolean
    {
        for(x in permissions)
        {
            var check=context.checkCallingOrSelfPermission(x)
            if(check!=PackageManager.PERMISSION_GRANTED)
            {
                return false
            }
        }
        return true
    }
}