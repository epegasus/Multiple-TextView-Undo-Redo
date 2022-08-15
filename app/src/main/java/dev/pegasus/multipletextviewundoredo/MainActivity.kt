package dev.pegasus.multipletextviewundoredo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.pegasus.multipletextviewundoredo.databinding.ActivityMainBinding
import dev.pegasus.regret.RegretManager

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val regretManagerArrayList = ArrayList<RegretManager>()
    private val regretManagerList: List<RegretManager> get() = regretManagerArrayList

    private var regretPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRegretManagers()

        binding.et1Main.setOnClickListener { on1Click() }
        binding.et2Main.setOnClickListener { on2Click() }

        binding.btnUpdate1Main.setOnClickListener { onUpdate1Click() }
        binding.btnUpdate2Main.setOnClickListener { onUpdate2Click() }
        binding.btnUndoMain.setOnClickListener { regretManagerList[regretPosition].undo() }
    }

    private fun initRegretManagers() {
        regretManagerArrayList.apply {
            add(RegretManager())
            add(RegretManager())
        }
        regretManagerList[0].setView(binding.tv1Main)
        regretManagerList[0].setPreviousText(binding.tv1Main.text.toString())

        regretManagerList[1].setView(binding.tv2Main)
        regretManagerList[1].setPreviousText(binding.tv2Main.text.toString())
    }

    private fun on1Click() {
        Toast.makeText(this, "No.1 is focused", Toast.LENGTH_SHORT).show()
        regretPosition = 0
    }

    private fun on2Click() {
        Toast.makeText(this, "No.2 is focused", Toast.LENGTH_SHORT).show()
        regretPosition = 1
    }

    private fun onUpdate1Click() {
        val newText = binding.et1Main.text.toString()
        binding.tv1Main.text = newText
        regretManagerList[0].setNewText(newText)
    }

    private fun onUpdate2Click() {
        val newText = binding.et2Main.text.toString()
        binding.tv2Main.text = newText
        regretManagerList[1].setNewText(newText)
    }
}