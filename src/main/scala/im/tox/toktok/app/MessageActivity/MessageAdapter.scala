package im.tox.toktok.app.MessageActivity

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.{LayoutInflater, View, ViewGroup}
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import im.tox.toktok.R
import im.tox.toktok.app.Message

import scala.collection.mutable.ListBuffer

class MessageAdapter(list: ListBuffer[Message]) extends RecyclerView.Adapter[RecyclerView.ViewHolder] {

  val items: ListBuffer[Message] = list

  override def getItemViewType(position: Int): Int = {
    return items(position).getType()
  }

  def onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder = {

    var recyclerViewItem: RecyclerView.ViewHolder = null;

    if (viewType == 1) {
      val itemView: View = LayoutInflater.from(viewGroup.getContext).inflate(R.layout.message_item_user_simple, viewGroup, false)
      recyclerViewItem = new MessageViewHolderSimple(itemView)
    }

    else if (viewType == 2) {
      val itemView: View = LayoutInflater.from(viewGroup.getContext).inflate(R.layout.message_item_friend_simple, viewGroup, false)
      recyclerViewItem = new MessageViewHolderSimple(itemView)
    }

    else if (viewType == 3) {
      val itemView: View = LayoutInflater.from(viewGroup.getContext).inflate(R.layout.message_item_action, viewGroup, false)
      recyclerViewItem = new MessageViewHolderAction(itemView)
    }

    return recyclerViewItem
  }

  def onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) = {
    val item: Message = items(position)

    if (getItemViewType(position) == 1 || getItemViewType(position) == 2) {

      val view: MessageViewHolderSimple = viewHolder.asInstanceOf[MessageViewHolderSimple]

      view.mUserText.setText(item.getMsgContent())
      view.mUserDetais.setText(item.getMsgDetails())
      view.mUserImg.setImageResource(item.getImageSrc())

    }

    else if (getItemViewType(position) == 3) {

      val view: MessageViewHolderAction = viewHolder.asInstanceOf[MessageViewHolderAction]
      view.mUserText.setText(item.getMsgContent())
      view.mUserImg.setImageResource(item.getImageSrc())

    }

  }

  def getItemCount(): Int = {
    return items.length
  }


  def addItem(newMsg: Message): Unit = {
    items.insert(0,newMsg)
    notifyItemInserted(0)
  }

}

class MessageViewHolderSimple(itemView: View) extends RecyclerView.ViewHolder(itemView) {

  var mUserText: TextView = itemView.findViewById(R.id.message_item_text).asInstanceOf[TextView]
  var mUserDetais: TextView = itemView.findViewById(R.id.message_item_details).asInstanceOf[TextView]
  var mUserImg: CircleImageView = itemView.findViewById(R.id.message_item_img).asInstanceOf[CircleImageView]

}


class MessageViewHolderAction(itemView: View) extends RecyclerView.ViewHolder(itemView) {

  var mUserText: TextView = itemView.findViewById(R.id.message_item_text).asInstanceOf[TextView]
  var mUserImg: CircleImageView = itemView.findViewById(R.id.message_item_img).asInstanceOf[CircleImageView]


}