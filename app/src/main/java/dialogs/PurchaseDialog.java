package dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.app.flirtjar.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rutvik on 2/12/2017 at 6:30 PM.
 */

public class PurchaseDialog extends Dialog
{

    @BindView(R.id.btn_buy)
    Button btnBuy;

    public PurchaseDialog(Context context)
    {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_purchase);

        ButterKnife.bind(this);

        btnBuy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PurchaseDialog.this.dismiss();
            }
        });

    }
}
