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
        sstrExpression = sstrExpression.concat((String)pView.getTag());
        ((TextView)findViewById(R.id.expression)).setText(sstrExpression);
        ((TextView)findViewById(R.id.value)).setText("");
    }

    public void clear(View pView)
    {
        sstrExpression = "";
        ((TextView)findViewById(R.id.expression)).setText(sstrExpression);
        ((TextView)findViewById(R.id.value)).setText(sstrExpression);
    }

    public void evaluate(View pView)
    {
        Expression lExpression = new ExpressionBuilder(sstrExpression).build();
        Double lValue = lExpression.evaluate();

        sstrExpression = "";
        ((TextView)findViewById(R.id.expression)).setText(sstrExpression);
        ((TextView)findViewById(R.id.value)).setText(lValue.toString());
    }
}
