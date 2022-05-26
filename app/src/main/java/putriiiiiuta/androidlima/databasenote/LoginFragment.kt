package putriiiiiuta.androidlima.databasenote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import putriiiiiuta.androidlima.databasenote.datastore.ManagerNote
import putriiiiiuta.androidlima.databasenote.room.NoteDatabase


class LoginFragment : Fragment() {
    private var noteDatabase : NoteDatabase? = null
    lateinit var noteManager: ManagerNote
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        noteDatabase = NoteDatabase.getinstance(requireContext())
        noteManager = ManagerNote(requireContext())
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteManager.ceklogin.asLiveData().observe(viewLifecycleOwner){
            if (it != false){
                Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_noteFragment)
            }
        }
        btn_login.setOnClickListener {
            val username = login_username.text.toString()
            val password = login_password.text.toString()
            loginuser(username,password)
        }
        btn_register.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

    fun loginuser(username : String, password :String) {
        GlobalScope.async {
            val user = noteDatabase?.notedao()?.getUserRegistered(username)
            requireActivity().runOnUiThread{
                if (user != null){
                    GlobalScope.launch {
                        noteManager.setBoolean(true)
                        noteManager.saveData(username)
                    }
                    Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_noteFragment)
                }else{
                    Toast.makeText(requireContext(), "Password yang anda masukkan salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}