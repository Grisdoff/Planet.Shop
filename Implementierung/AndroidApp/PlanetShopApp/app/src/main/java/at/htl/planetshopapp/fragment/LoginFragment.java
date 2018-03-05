package at.htl.planetshopapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import at.htl.planetshopapp.R;
import at.htl.planetshopapp.activity.MainActivity;

import static android.content.ContentValues.TAG;


public class LoginFragment extends Fragment {

    private static final int RC_SIGN_IN = 1001; //could be any number (i guess?)

    private static LoginFragment loginFragment;


    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInClient googleSignInClient;
    private GoogleSignInAccount googleSignInAccount;
    private Button logoutButton;
    private Button deleteAccountButton;
    private SignInButton loginButton;



    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment getLoginFragment() {
        return loginFragment;
    }

    public static void setLoginFragment(LoginFragment loginFragment) {
        LoginFragment.loginFragment = loginFragment;
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = view.findViewById(R.id.google_sign_in_button);
        loginButton.setSize(SignInButton.SIZE_STANDARD);
        loginButton.setOnClickListener(v -> signIn());

        logoutButton = view.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(v -> signOut());

        deleteAccountButton = view.findViewById(R.id.delete_account_button);
        deleteAccountButton.setOnClickListener(v -> deleteAccount());
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(MainActivity.getMainActivity().getApplicationContext(), googleSignInOptions);
        googleSignInAccount = GoogleSignIn.getLastSignedInAccount(MainActivity.getMainActivity().getApplicationContext());
        updateUI();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(data));
        }
    }

    private void signOut() {
        googleSignInClient.signOut();
        googleSignInAccount = null;
        updateUI();
    }

    private void deleteAccount() {
        googleSignInClient.revokeAccess();
        googleSignInAccount = null;
        updateUI();
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            googleSignInAccount = account;
            updateUI();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("SignIn", "signInResult:failed code=" + e.getStatusCode());
            googleSignInAccount = null;
            updateUI();
        }
    }


    private void updateUI() {
        boolean userNotLoggedIn = googleSignInAccount == null;
        loginButton.setEnabled(userNotLoggedIn);
        logoutButton.setEnabled(!userNotLoggedIn);
        deleteAccountButton.setEnabled(!userNotLoggedIn);
    }

    private void signIn() {
        startActivityForResult(googleSignInClient.getSignInIntent(), RC_SIGN_IN);
    }

    public GoogleSignInAccount getGoogleSignInAccount() {
        return googleSignInAccount;
    }
}
