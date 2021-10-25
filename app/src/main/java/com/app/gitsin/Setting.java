package com.app.gitsin;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.preference.Preference;

import androidx.preference.PreferenceManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class Setting extends PreferenceActivity {

    String key;
    User user;
    SharedPreferences sp;
    DatabaseReference database;
    View dialogView;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Calendar calendar;
    final long REPEAT = 1000 * 60 * 60 * 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_preference);

        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        user = (User) intent.getSerializableExtra("info");
        database = FirebaseDatabase.getInstance().getReference("users");

        //설정 상태에 대한 제어 가능
        sp = PreferenceManager.getDefaultSharedPreferences(this /* Activity context */);

        //깃허브 계정 미리보기
        Preference a = findPreference("gitHubAccount");
        a.setSummary(user.getGithubId());
        if (user.getGithubId().equals("깃허브 아이디를 다시 등록해주세요")) {
            a.setIcon(R.drawable.check);
        } else {
            a.setIcon(null);
        }

        //깃허브 계정 수정
        Preference githubChange = findPreference("gitHubAccount");
        githubChange.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                dialogView = (View) View.inflate(Setting.this, R.layout.setting_gitupdate, null);
                EditText newGitId = dialogView.findViewById(R.id.settingEditText);

                AlertDialog.Builder dialog = new AlertDialog.Builder(Setting.this);
                dialog.setTitle("연결할 GitHub 계정을 입력하세요");
                dialog.setView(dialogView);

                //확인 눌렀을 때 계정 업데이트
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newGit = newGitId.getText().toString();
                        user.setGithubId(newGit);
                        database.child(key).setValue(user);
                        Intent i = new Intent(Setting.this, Login.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        Toast.makeText(getApplicationContext(), "계정 업데이트 성공. 다시 로그인해주세요", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }
                });
                //취소 눌렀을 때 nothing happens
                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.show();
                return false;
            }
        });

        //알람설정
        sp.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                if (key.equals("alarm")) {
                    //알람 on <-> off (알람 등록 <-> 삭제)
                    boolean alarmYes = sp.getBoolean("alarm", true);
                    if(alarmYes) {
                        //알람 생성
                        alarmManager = (AlarmManager) (Setting.this).getSystemService(Context.ALARM_SERVICE);
                        //알람 조건 충족 시 리시버로 전달될 intent
                        Intent intent1 = new Intent(Setting.this, AlarmReceiver.class);
                        pendingIntent = PendingIntent.getBroadcast(Setting.this,
                                AlarmReceiver.NOTIFICATION_ID, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                        //알람 시간 확인
                        int time = setTime(sp.getString("alarmTime", "9am"));
                        Log.d("====================",time+"시에 알람 설정");

                        //알람 시간 설정
                        calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.HOUR_OF_DAY, time);

                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                                REPEAT, pendingIntent);
                        Log.d("====================",calendar.getTimeInMillis()+"시에 알람 설정");
                        ComponentName receiver = new ComponentName(Setting.this, AlarmReceiver.class);
                        PackageManager pm = (Setting.this).getPackageManager();

                        pm.setComponentEnabledSetting(receiver,
                                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                                PackageManager.DONT_KILL_APP);

                    } else {
                        //알람 해제
                        if (alarmManager != null) {
                            alarmManager.cancel(pendingIntent);
                        }
                        Log.d("====================","알람 해제");
                        ComponentName receiver = new ComponentName(Setting.this, AlarmReceiver.class);
                        PackageManager pm = (Setting.this).getPackageManager();
                        pm.setComponentEnabledSetting(receiver,
                                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                                PackageManager.DONT_KILL_APP);
                    }

                } else if (key.equals("alarmTime")) {
                    // (알람 on 상태에서) 알람 시간 변경 시
                    int time = setTime(sp.getString("alarmTime", "9am"));
                    Log.d("====================",time+"시에 알람 설정");

                    calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, time);

                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            REPEAT, pendingIntent);

                    ComponentName receiver = new ComponentName(Setting.this, AlarmReceiver.class);
                    PackageManager pm = (Setting.this).getPackageManager();

                    pm.setComponentEnabledSetting(receiver,
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                            PackageManager.DONT_KILL_APP);

                } else if (key.equals("darkMode")) {
                    boolean dark = sp.getBoolean("darkMode", true);
                    if (dark) {
                        ThemeUtil.applyTheme("dark");
                        ThemeUtil.darkSave(getApplicationContext(), "dark", sp);
                    } else {
                        ThemeUtil.applyTheme("light");
                        ThemeUtil.darkSave(getApplicationContext(), "light", sp);
                    }
                }
            }
        });

        //로그아웃
        Preference logOut = findPreference("logOut");
        logOut.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent i = new Intent(Setting.this, Login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                Toast.makeText(getApplicationContext(), "로그아웃 성공", Toast.LENGTH_SHORT).show();
                startActivity(i);
                return false;
            }
        });

        //깃신 탈퇴
        Preference withdraw = findPreference("withdrawal");
        withdraw.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                database.child(key).removeValue();
                Intent i = new Intent(Setting.this, Login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                Toast.makeText(getApplicationContext(), "회원탈퇴 완료", Toast.LENGTH_SHORT).show();
                startActivity(i);
                return false;
            }
        });

    } // end of onCreate

    public int setTime(String time) {
        int alarmTime = 0;
        switch (time) {
            case "am9": alarmTime = 9; break;
            case "am11": alarmTime = 11; break;
            case "pm1": alarmTime = 13; break;
            case "pm3": alarmTime = 15; break;
            case "pm5": alarmTime = 17; break;
            case "pm7": alarmTime = 19; break;
            case "pm9": alarmTime = 21; break;
            case "pm11": alarmTime = 23; break;
        }
        return alarmTime;
    }

}
