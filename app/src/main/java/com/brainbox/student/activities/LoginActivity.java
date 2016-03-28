package com.brainbox.student.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.brainbox.student.R;
import com.brainbox.student.ui.CustomTitle;
import com.brainbox.student.ui.CustomTypeFace;
import com.brainbox.student.ui.button.ButtonPlus;
import com.brainbox.student.ui.edittext.CustomEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin-PC on 2/23/2016.
 */
public class LoginActivity extends AppCompatActivity
{

	@Bind(R.id.etUserName)
	CustomEditText etUserName;
	@Bind(R.id.etPassword)
	CustomEditText etPassword;
	@Bind(R.id.btnLogin)
	ButtonPlus btnLogin;
	@Bind(R.id.toolbar)
	Toolbar toolbar;
	@Bind(R.id.txtForgotPassword)
	TextView txtForgotPassword;
	@Bind(R.id.txtSearchSchool)
	TextView txtSearchSchool;

	@OnClick(R.id.txtForgotPassword)
	void forgotPassword() {

	}

	@OnClick(R.id.btnLogin)
	void login() {
		attemptLogin();
	}

	@OnClick(R.id.txtSearchSchool)
	void search(){
		Intent intent = new Intent(this, SearchSchoolActivity.class);
		startActivity(intent);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		populate();

	}

	private void populate() {
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);

		txtForgotPassword.setTypeface(CustomTypeFace.getTypeface(this));
		txtSearchSchool.setTypeface(CustomTypeFace.getTypeface(this));

		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			btnLogin.setBackgroundResource(R.drawable.abc_alpha_btn_background_ripple);
		}

		getSupportActionBar().setTitle(CustomTitle.getTitle(this, getResources().getString(R.string.login)));

		toolbar.setNavigationIcon(R.mipmap.abc_ic_ab_back_mtrl_am_alpha);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

	}


	public void attemptLogin() {


		String username = etUserName.getText().toString();
		String password = etPassword.getText().toString();


		/*if (!Validate.isValidUsername(username)) {
			MessageCustomDialogDTO messageCustomDialogDTO = new MessageCustomDialogDTO();
			messageCustomDialogDTO.setMessage(getString(R.string.error_invalid_username));
			SnackBar.show(this, messageCustomDialogDTO);

		} else if (!Validate.isPasswordValid(password)) {
			MessageCustomDialogDTO messageCustomDialogDTO = new MessageCustomDialogDTO();
			messageCustomDialogDTO.setMessage(getString(R.string.error_invalid_password));
			SnackBar.show(this, messageCustomDialogDTO);
		} else {*/
			// BrainBox.login(this);
			Intent intent = new Intent(this, DashboardActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);

	}


}

