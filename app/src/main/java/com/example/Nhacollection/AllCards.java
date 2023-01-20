package com.example.Nhacollection;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.braintreepayments.cardform.OnCardFormSubmitListener;
import com.braintreepayments.cardform.utils.CardType;
import com.braintreepayments.cardform.view.AccessibleSupportedCardTypesView;
import com.braintreepayments.cardform.view.CardEditText;
import com.braintreepayments.cardform.view.CardForm;

public class AllCards extends AppCompatActivity  implements OnCardFormSubmitListener,
        CardEditText.OnCardTypeChangedListener {

    private ProgressDialog loadingBar;
    private static final CardType[] SUPPORTED_CARD_TYPES = { CardType.VISA, CardType.MASTERCARD, CardType.DISCOVER,
            CardType.AMEX, CardType.DINERS_CLUB, CardType.JCB, CardType.MAESTRO, CardType.UNIONPAY,
            CardType.HIPER, CardType.HIPERCARD };

    private AccessibleSupportedCardTypesView mSupportedCardTypesView;
    private Button buynow ;
    AlertDialog.Builder alertBuilder;

    protected CardForm mCardForm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_checkout);
        loadingBar = new ProgressDialog(this);

        mSupportedCardTypesView = findViewById(R.id.supported_card_types);
        mSupportedCardTypesView.setSupportedCardTypes(SUPPORTED_CARD_TYPES);


        mCardForm = findViewById(R.id.card_form);
        mCardForm.cardRequired(true)
                .maskCardNumber(true)
                .maskCvv(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .saveCardCheckBoxChecked(true)
                .saveCardCheckBoxVisible(true)
                .cardholderName(CardForm.FIELD_REQUIRED)
                .mobileNumberExplanation("Make sure SMS is enabled for this mobile number")
                .setup(this);
        mCardForm.setOnCardFormSubmitListener(this);
        mCardForm.setOnCardTypeChangedListener(this);
        buynow=findViewById(R.id.btnBuy);

        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingBar.setTitle("Wait for a moment ");
                loadingBar.setMessage("Payment Completed");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

                Toast.makeText(AllCards.this, "Order has been placed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AllCards.this,Home_Activity.class);
                startActivity(intent);
            }

        });
        // Failure to set FLAG_SECURE exposes your app to screenshots allowing other apps to steal card information.


        // Warning: this is for development purposes only and should never be done outside of this example app.
        // Failure to set FLAG_SECURE exposes your app to screenshots allowing other apps to steal card information.
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }

    @Override
    public void onCardTypeChanged(CardType cardType) {
        if (cardType == CardType.EMPTY) {
            mSupportedCardTypesView.setSupportedCardTypes(SUPPORTED_CARD_TYPES);
        } else {
            mSupportedCardTypesView.setSelected(cardType);
        }
    }

    @Override
    public void onCardFormSubmit() {
        if (mCardForm.isValid()) {

        } else {
            mCardForm.validate();

        }
    }
}