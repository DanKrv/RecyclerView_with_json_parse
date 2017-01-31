package yem.com.jsonfromwebservicetutorial_4;

/**
 * Created by Juned on 8/14/2016.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    List<YoutubeData> youTubeData;

    View view;

    public RecyclerViewAdapter(List<YoutubeData> youTubeData) {

        this.youTubeData = youTubeData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int i) {

        Viewholder.SubjectTextView.setText((CharSequence) youTubeData.get(i));
    }

    @Override
    public int getItemCount() {

        return youTubeData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView SubjectTextView;

        public ViewHolder(View view) {

            super(view);

            SubjectTextView = (TextView)view.findViewById(R.id.textview1);
        }
    }

}