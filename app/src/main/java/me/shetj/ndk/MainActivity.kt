package me.shetj.ndk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import me.shetj.audio.LameUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val testInit = LameUtils.testInit()
        Log.i("test",testInit);
        Toast.makeText(this,testInit,Toast.LENGTH_SHORT).show()
    }
}
