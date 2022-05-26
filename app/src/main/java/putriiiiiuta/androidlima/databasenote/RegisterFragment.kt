package putriiiiiuta.androidlima.databasenote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import putriiiiiuta.androidlima.databasenote.datastore.ManagerNote
import putriiiiiuta.androidlima.databasenote.room.NoteDatabase
import putriiiiiuta.androidlima.databasenote.room.User


class RegisterFragment : Fragment() {
    lateinit var noteManager: ManagerNote
    private var noteDatabase : NoteDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_register.setOnClickListener {
            tambahuser()
        }
    }
    fun tambahuser(){
        val nama = reg_name.text.toString()
        val username = reg_username.text.toString()
        val password = reg_password.text.toString()
        val konfirmasi = reg_confrimpass.text.toString()
        if(username.isNotEmpty() && password.isNotEmpty() && nama.isNotEmpty() && konfirmasi.isNotEmpty()){
            if (password == konfirmasi){
                GlobalScope.launch {
                    val cekuser = noteDatabase?.notedao()?.getUserRegistered(username)
                    if (cekuser != null){
                        requireActivity().runOnUiThread {
                            Toast.makeText(requireContext(), "username sudah terdaftar",
                                Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        val result = noteDatabase?.notedao()?.registerUser(User(username, nama, password))
                        requireActivity().runOnUiThread {
                            if (result != 0.toLong()){
                                Toast.makeText(requireContext(), "Berhasil registrasi",
                                    Toast.LENGTH_SHORT).show()
                                Navigation.findNavController(requireView()).navigate(R.id.action_registerFragment_to_loginFragment)
                            }else{
                                Toast.makeText(requireContext(), "Gagal, silahkan coba lagi",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }else{
                Toast.makeText(requireContext(),"Password harus sama",
                    Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(requireContext(),"Semua Field harus di isi",
                Toast.LENGTH_SHORT).show()
        }
    }

}