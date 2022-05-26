package putriiiiiuta.androidlima.databasenote.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*
import putriiiiiuta.androidlima.databasenote.R
import putriiiiiuta.androidlima.databasenote.room.Note

class AdapterNote(private val dataNote : List<Note>,
                  private val onClik :(Note)->Unit
): RecyclerView.Adapter<AdapterNote.ViewHolder>(){
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataNote[position]

        holder.itemView.apply {
            judulNote.text = data.judul
            Waktu.text = data.waktu
            rootView.setOnClickListener {
                onClik(data)
            }
        }
    }

    override fun getItemCount(): Int {
        return  dataNote.size
    }
}