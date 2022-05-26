package putriiiiiuta.androidlima.databasenote

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import putriiiiiuta.androidlima.databasenote.datastore.ManagerNote
import putriiiiiuta.androidlima.databasenote.room.Note
import putriiiiiuta.androidlima.databasenote.room.NoteDatabase

@Suppress("DeferredResultUnused")
@DelicateCoroutinesApi
class DetailFragment : Fragment() {
    private var noteDatabase : NoteDatabase? = null
    private lateinit var noteManager: ManagerNote
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        noteDatabase = NoteDatabase.getinstance(requireContext())
        noteManager = ManagerNote(requireContext())
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noteDetail = arguments?.getParcelable<Note>("detail") as Note
        detail_judul.text = "Judul : " + noteDetail.judul
        detail_note.text = "Catatan : " + noteDetail.isi
        detail_waktu.text = "Waktu : " + noteDetail.waktu

        btn_Home.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_detailFragment_to_noteFragment)
        }

        btn_Share.setOnClickListener {
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,noteDetail.isi)
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }
        btn_Edit.setOnClickListener {
            val bundle = bundleOf("detailEdit" to noteDetail)
            Navigation.findNavController(requireView()).navigate(R.id.action_detailFragment_to_editFragment, bundle)
        }
        btn_Delete.setOnClickListener {
            GlobalScope.async {
                val result = noteDatabase?.notedao()?.deleteNote(noteDetail)
                requireActivity().runOnUiThread{
                    if (result == 1){
                        Toast.makeText(requireContext(),"note dihapus",Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(requireView()).navigate(R.id.action_detailFragment_to_noteFragment)
                    }else{
                        Toast.makeText(requireContext(), "Gagal Menghapus", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}