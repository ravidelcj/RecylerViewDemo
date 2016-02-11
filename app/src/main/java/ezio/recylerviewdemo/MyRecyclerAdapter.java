package ezio.recylerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hp-Hp on 11-02-2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {


    List<String> str;
    LayoutInflater inflater;
    Context context;
    public MyRecyclerAdapter(Context c, List<String> str){
        context=c;
        this.str=str;
        inflater=LayoutInflater.from(c);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.element,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public int getItemCount() {
        return str.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String list=str.get(position);
        Log.d("listRavi",list);
        holder.text.setText(list);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView text;
        public MyViewHolder(View itemView) {
            super(itemView);
            text= (TextView) itemView.findViewById(R.id.recyclerText);

        }
    }
}
