package com.example.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    }

    public void append(View pView)
    {
        //Set Value to blank
        ((TextView)findViewById(R.id.value)).setText("");

        //Concat provided operator/operand to the Expression
        sstrExpression = sstrExpression.concat((String)pView.getTag());
        ((TextView)findViewById(R.id.expression)).setText(sstrExpression);
    }

    public void clear(View pView)
    {
        //Set Expression to blank
        sstrExpression = "";
        ((TextView)findViewById(R.id.expression)).setText(sstrExpression);

        //Set Value to blank
        ((TextView)findViewById(R.id.value)).setText("");
    }

    public void evaluate(View pView)
    {
        if(sstrExpression == null || sstrExpression.isEmpty() == true)
            return;

        String lstrExpression = sstrExpression;

        //Evaluation being done. Set Expression to blank.
        sstrExpression = "";
        ((TextView)findViewById(R.id.expression)).setText(sstrExpression);

        Expression lExpression = new ExpressionBuilder(lstrExpression).build();

        if(lExpression.validate().isValid() == false)
        {
            ((TextView)findViewById(R.id.value)).setText("BAD EXPRESSION");
            return;
        }

        Double lValue = lExpression.evaluate();
        ((TextView)findViewById(R.id.value)).setText(lValue.toString());

    }
}
