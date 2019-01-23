package com.example.adrian.textinputlayoutexample;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    TextInputLayout usernameWrapper;
    TextInputLayout passwordWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        Button btn = (Button) findViewById(R.id.btn);

        usernameWrapper.setHint("Username");
        passwordWrapper.setHint("Password");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();

                usernameWrapper.setErrorEnabled(false);
                passwordWrapper.setErrorEnabled(false);

                String username = usernameWrapper.getEditText().getText().toString();
                String password = passwordWrapper.getEditText().getText().toString();

                boolean a = validateEmail(username);
                boolean b = validatePassword(password);

                if ( a && b) {
                    // OK, se pasa a la siguiente acción
                    doLogin();
                }
            }
        });
    }

    public void doLogin() {
        Toast.makeText(getApplicationContext(), "OK! I'm performing login.", Toast.LENGTH_SHORT).show();
        // TODO: login procedure; not within the scope of this tutorial.
    }

    public boolean validatePassword(String password) {
        if (!(password.length() > 5)) {
            passwordWrapper.setError("Contrasena inválido");
            return false;
        } else {
            passwordWrapper.setError(null);
        }

        return true;
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        //return matcher.matches();

        if (!matcher.matches()) {
            usernameWrapper.setError("Correo electrónico inválido");
            return false;
        } else {
            usernameWrapper.setError(null);
        }

        return true;
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
