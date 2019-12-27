package com.cuty.simplenotepad

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class RecyclerViewAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<RecyclerViewAdapter.WordViewHolder>(){



    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>()
    var mContext=context

    inner class WordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val wordItemView : TextView = itemView.findViewById(R.id.textView)
        val tituloItemView : TextView = itemView.findViewById(R.id.tvTitulo)
        var customItemClickListener:CustomClickListener? = null
        init{
            itemView.buDeleted.setOnClickListener(this)
        }

        fun oncustomItemClickListener (itemClickListener:CustomClickListener){
            this.customItemClickListener = itemClickListener
        }

        override fun onClick(p0: View?) {
            this.customItemClickListener!!.OnItemCustomClickListener(p0!!,adapterPosition)
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item,parent,false)

        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        holder.wordItemView.text = current.word
        holder.tituloItemView.text = current.fecha

        holder.oncustomItemClickListener(object: CustomClickListener{
            override fun OnItemCustomClickListener(view: View, pos:Int) {
                //Toast.makeText(mContext, "el boton borrar funciona", Toast.LENGTH_SHORT).show()
                MainActivity.activityViewModel.deleteWord(current.word)

            }
        })

    }
    internal fun setWords(words:List<Word>){
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount()= words.size
    private fun removeAt(pos:Int){

    }



}