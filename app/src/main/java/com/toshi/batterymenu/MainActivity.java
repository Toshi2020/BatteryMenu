package com.toshi.batterymenu;
/*****************************************************************************
*
*	BatteryMenu:MainActivity -- 電池メニュー表示
*
*	V1.0.0	2021/04/08	initial revision by	toshi
*
*****************************************************************************/

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

//****************************************************************************
// メインクラス
//
public class MainActivity extends AppCompatActivity
					implements View.OnClickListener {

	private Button mBtnBattery, mBtnBatteryOptimize;

	/*------------------------------------------------------------------------
	インスタンス作成時
	------------------------------------------------------------------------*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ボタンのリソースを得る
		mBtnBattery = findViewById(R.id.button1);
		mBtnBatteryOptimize = findViewById(R.id.button2);
		// ボタンのリスナの設定
		mBtnBattery.setOnClickListener(this);
		mBtnBatteryOptimize.setOnClickListener(this);
	}
	/*------------------------------------------------------------------------
	ボタンクリックリスナ
	------------------------------------------------------------------------*/
	@Override
	public void onClick(View v) {

		// ボタン1が押された
		if (v.equals(mBtnBattery)) {
			// バッテリーの画面を開く
			OpenBatteryMenu();

		// ボタン2が押された
		} else if (v.equals(mBtnBatteryOptimize)) {
			// バッテリー最適化の画面を開く
			OpenBatterySetting();
		}
	}
	/*------------------------------------------------------------------------
	電池の画面を開く
	------------------------------------------------------------------------*/
	private void OpenBatteryMenu() {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);

		// HUAWEI MediaPad M3 lite 8の場合
		intent.setClassName("com.huawei.systemmanager",
			  "com.huawei.systemmanager.power.ui.HwPowerManagerActivity");
		try {
			startActivity(intent);
			return;
		} catch (Exception e) {
		}

		// 一般的なAndroid端末の場合
		intent.setClassName("com.android.settings",
			  "com.android.settings.Settings$PowerUsageSummaryActivity");
//		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
//							Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
		try {
			startActivity(intent);
			return;
		} catch (Exception e) {
		}

		// PUMPKINヘッドユニットの場合
		intent.setClassName("com.android.settings",
			  "com.android.settings.fuelgauge.PowerUsageSummary");
		try {
			startActivity(intent);
			return;
		} catch (Exception e) {
			ToastShow(getString(R.string.mes_no_open));
		}
	}
	/*------------------------------------------------------------------------
	電池の最適化の画面を開く
	------------------------------------------------------------------------*/
	private void OpenBatterySetting() {
		// バッテリー最適化メニュー
		Intent intent = new Intent(
			Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
//		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
//						Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
		try {
			startActivity(intent);
		} catch (Exception e) {
			ToastShow(getString(R.string.mes_no_open));
		}
	}
//****************************************************************************
// 画面表示
//
	/*------------------------------------------------------------------------
	toast出力
		引数
			String str;	表示文字
	------------------------------------------------------------------------*/
	private void ToastShow(String str) {
		Toast tst;
		tst = Toast.makeText(this, str, Toast.LENGTH_LONG);
		// 画面の中央に表示
		tst.setGravity(Gravity.CENTER, 0, 0);
		tst.show();
	}
}
