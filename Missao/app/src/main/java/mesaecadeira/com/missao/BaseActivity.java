package mesaecadeira.com.missao;

    import android.app.Activity;
    import android.app.AlertDialog;
    import android.app.ProgressDialog;
    import android.content.Context;
    import android.content.DialogInterface;
    import android.os.Bundle;
    import android.view.View;
    import android.view.inputmethod.InputMethodManager;

    public class BaseActivity extends Activity {

        private static ProgressDialog mDialog;

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
        }

        @Override
        protected void onRestoreInstanceState(Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);
        }

        public void hideKeyboard(View v){
            InputMethodManager imm =  (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

        public void initNewProgressDialog(String title, String text){
            finalizeDialogIfExists();
            mDialog = ProgressDialog.show(this, title, text);
        }

        public void finalizeDialogIfExists(){
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
                mDialog = null;
            }
        }

        public void createAndShowSimpleDialog(String title, String message){
            String textButton = getResources().getString(R.string.dialog_generic_button_ok);
            showDialog(title, message, textButton);
        }

        public void createAndShowAttentionDialog(String errorMessage){
            String title = getResources().getString(R.string.dialog_generic_title_attention);
            String message = errorMessage;
            String textButton = getResources().getString(R.string.dialog_generic_button_ok);
            showDialog(title, message, textButton);
        }

        public void createAndShowAttentionDialogWithCallback(String errorMessage, DialogInterface.OnClickListener listener){
            String title = getResources().getString(R.string.dialog_generic_title_attention);
            String message = errorMessage;
            String textButton = getResources().getString(R.string.dialog_generic_button_ok);
            showDialog(title, message, textButton, listener);
        }

        public void showDialog(String title, String message, String textButton){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(message).setTitle(title)
                    .setPositiveButton(textButton, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    }).create().show();
        }

        public void showDialog(String title, String message, String textButton, DialogInterface.OnClickListener listener){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(message).setTitle(title)
                    .setPositiveButton(textButton, listener).create().show();
        }

        public void showDialog(int title, int message, int textButton, DialogInterface.OnClickListener listener){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(message).setTitle(title)
                    .setPositiveButton(textButton, listener).create().show();
        }
    }