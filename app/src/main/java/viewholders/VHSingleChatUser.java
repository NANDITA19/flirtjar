package viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.flirtjar.R;

import dialogs.PurchaseDialog;

/**
 * Created by rutvik on 2/8/2017 at 11:01 PM.
 */

public class VHSingleChatUser extends RecyclerView.ViewHolder
{
    final Context context;

    public VHSingleChatUser(final Context context, View itemView)
    {
        super(itemView);
        this.context = context;

        itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PurchaseDialog pd = new PurchaseDialog(context);
                pd.show();
            }
        });

    }

    public static VHSingleChatUser create(final Context context, final ViewGroup parent)
    {
        return new VHSingleChatUser(context, LayoutInflater.from(context)
                .inflate(R.layout.single_chat_user_item, parent, false));
    }

    public static void bind(final VHSingleChatUser vh)
    {

    }

}
