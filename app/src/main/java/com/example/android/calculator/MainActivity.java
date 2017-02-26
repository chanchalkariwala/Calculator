package com.example.android.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private static String sstrExpression = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView lClearView = (TextView) findViewById(R.id.clear_button);

        lClearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Set Value to blank
                updateValue("");

                if(sstrExpression == null || sstrExpression.isEmpty()) {
                    return;
                }

                sstrExpression = sstrExpression.substring(0, sstrExpression.length()-1);
                updateExpression(sstrExpression);
            }
        });

        lClearView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v)
            {
                updateExpression("");
                updateValue("");
                return true;
            }
        });
    }

    public void append(View pView)
    {
        String lValue = String.valueOf(((TextView) findViewById(R.id.value)).getText());

        try
        {
            Double.parseDouble(lValue);

        }
        catch(NumberFormatException lException)
        {
            lValue = "";
        }

        try
        {
            Double.parseDouble((String)pView.getTag());
            lValue = "";
        }
        catch(NumberFormatException lException)
        {

        }

        updateValue("");

        //Concat provided operator/operand to the Expression
        sstrExpression = sstrExpression.concat(lValue + (String)pView.getTag());
        updateExpression(sstrExpression);
    }

    public void showValue(View pView)
    {
        evaluate();
    }

    public void evaluate()
    {
        if(sstrExpression == null || sstrExpression.isEmpty() == true)
            return;

        String lstrExpression = sstrExpression;

        updateExpression("");

        Expression lExpression = new ExpressionBuilder(lstrExpression).build();

        if(lExpression.validate().isValid() == false)
        {
            lstrExpression = lstrExpression.substring(0, lstrExpression.length()-1);

            lExpression = new ExpressionBuilder(lstrExpression).build();

            if(lExpression.validate().isValid() == false)
            {
                updateValue("BAD EXPRESSION");
            }
            return;
        }

        String lValueDisplay = "";
        try
        {
            Double lValue = lExpression.evaluate();

            if ((lValue == Math.floor(lValue)) && !Double.isInfinite(lValue))
            {
                Long llongvalue = lValue.longValue();
                lValueDisplay = llongvalue.toString();
            }
            else
            {
                lValueDisplay = lValue.toString();
            }

        }
        catch (Exception lException)
        {
            lValueDisplay = lException.getLocalizedMessage();
        }

        updateValue(lValueDisplay);
    }

    public void updateExpression(String pValue)
    {
        sstrExpression = pValue;

        if(sstrExpression == null)
        {
            sstrExpression = "";
        }

        ((TextView)findViewById(R.id.expression)).setText(sstrExpression);
    }

    public void updateValue(String pValue)
    {
        if(pValue == null)
        {
            sstrExpression = "";
        }

        ((TextView)findViewById(R.id.value)).setText(pValue);
    }
}
