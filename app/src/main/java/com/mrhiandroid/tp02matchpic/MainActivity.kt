package com.mrhiandroid.tp02matchpic

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mrhiandroid.tp02matchpic.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    val imgAnimals: Array<Int> = arrayOf(R.drawable.a_ele, R.drawable.a_frog, R.drawable.a_lion, R.drawable.a_monkey, R.drawable.a_pig)
    val imgBoards: Array<Int> = arrayOf(R.drawable.b_ele, R.drawable.b_frog, R.drawable.b_lion, R.drawable.b_monkey, R.drawable.b_pig)

    var index: Int=0

    val sp: SoundPool by lazy { SoundPool.Builder().build() }
    val sdAgain:Int by lazy { sp.load(this, R.raw.s_again,0) }
    val sdGood:Int by lazy { sp.load(this, R.raw.s_goodjob,0) }
    val sdSelect:Int by lazy { sp.load(this, R.raw.s_select,0) }
    val sdSijak:Int by lazy { sp.load(this, R.raw.s_sijak,0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener { startGame() }

        binding.image1.setOnClickListener { clickImage(it) }
        binding.image2.setOnClickListener { clickImage(it) }
        binding.image3.setOnClickListener { clickImage(it) }
        binding.image4.setOnClickListener { clickImage(it) }
        binding.image5.setOnClickListener { clickImage(it) }
    }

    private fun clickImage(view: View){

        sp.play(sdSelect, 1.0F, 1.0F, 0, 0, 1.0F)

        val tag:String = view.tag.toString()
        val num:Int = tag.toInt()

        if( num== index ){
            binding.ivResult.visibility= View.VISIBLE
            binding.ivResult.setImageResource(R.drawable.result_good)

            sp.play(sdGood, 1.0F, 1.0F, 1, 0, 1.0F)
        }else{
            binding.ivResult.visibility= View.VISIBLE
            binding.ivResult.setImageResource(R.drawable.result_bad)

            sp.play(sdAgain, 1.0F, 1.0F, 1, 0, 1.0F)
        }
    }

    private fun startGame(){

        //이미지 배치 순서를 가진 배열
        val orders: Array<Int> = arrayOf(0,1,2,3,4)

        orders.shuffle()

        binding.image1.setImageResource(imgAnimals[ orders[0] ])
        binding.image2.setImageResource(imgAnimals[ orders[1] ])
        binding.image3.setImageResource(imgAnimals[ orders[2] ])
        binding.image4.setImageResource(imgAnimals[ orders[3] ])
        binding.image5.setImageResource(imgAnimals[ orders[4] ])

        binding.image1.tag = orders[0]
        binding.image2.tag = orders[1]
        binding.image3.tag = orders[2]
        binding.image4.tag = orders[3]
        binding.image5.tag = orders[4]

        index= Random.nextInt(0, 5)

        binding.ivBoard.setImageResource(imgBoards[index])

        binding.ivResult.visibility= View.GONE

        sp.play(sdSijak, 1.0F, 1.0F, 1, 0, 1.0F)
    }
}