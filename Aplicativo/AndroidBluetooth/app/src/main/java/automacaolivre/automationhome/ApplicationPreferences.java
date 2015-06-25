package automacaolivre.automationhome;

;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ApplicationPreferences {

    private static SharedPreferences mPreferences;
    private static ApplicationPreferences mInstance;
    private static Editor mEditor;

    private ApplicationPreferences() {
    }

    public static ApplicationPreferences getInstance() {

        if (mInstance == null) {
            Context context = MyApplication.mContext;
            mInstance = new ApplicationPreferences();


            mPreferences = context.getSharedPreferences("MyAppPref", Context.MODE_PRIVATE);
            mEditor = mPreferences.edit();
        }
        return mInstance;
    }

    public String getNameSaida1() {
        return mPreferences.getString("NAME_SAIDA1", "Saída 1");
    }

    public void setNameSaida1(String value) {
        mEditor.putString("NAME_SAIDA1", value).apply();
    }

    public String getNameSaida2() {
        return mPreferences.getString("NAME_SAIDA2", "Saída 2");
    }

    public void setNameSaida2(String value) {
        mEditor.putString("NAME_SAIDA2", value).apply();
    }

    public String getNameSaida3() {
        return mPreferences.getString("NAME_SAIDA3", "Saída 3");
    }

    public void setNameSaida3(String value) {
        mEditor.putString("NAME_SAIDA3", value).apply();
    }

    public String getNameSaida4() {
        return mPreferences.getString("NAME_SAIDA4", "Saída 4");
    }

    public void setNameSaida4(String value) {
        mEditor.putString("NAME_SAIDA4", value).apply();
    }

    public String getNameSaida5() {
        return mPreferences.getString("NAME_SAIDA5", "Saída 5");
    }

    public void setNameSaida5(String value) {
        mEditor.putString("NAME_SAIDA5", value).apply();
    }

    public String getNameSaida6() {
        return mPreferences.getString("NAME_SAIDA6", "Saída 6");
    }

    public void setNameSaida6(String value) {
        mEditor.putString("NAME_SAIDA6", value).apply();
    }

    public String getNameSaida7() {
        return mPreferences.getString("NAME_SAIDA7", "Saída 7");
    }

    public void setNameSaida7(String value) {
        mEditor.putString("NAME_SAIDA7", value).apply();
    }

    public String getNameSaida8() {
        return mPreferences.getString("NAME_SAIDA8", "Saída 8");
    }

    public void setNameSaida8(String value) {
        mEditor.putString("NAME_SAIDA8", value).apply();
    }

    public String getNameSaidaRGB() {
        return mPreferences.getString("NAME_SAIDARGB", "Saída RGB");
    }

    public void setNameSaidaRGB(String value) {
        mEditor.putString("NAME_SAIDARGB", value).apply();
    }

    public String getDeviceAddress() {
        return mPreferences.getString("EXTRA_DEVICE_ADDRESS", "");
    }

    public void setDeviceAddress(String value) {
        mEditor.putString("EXTRA_DEVICE_ADDRESS", value).apply();
    }


    public String getName() {
        return mPreferences.getString("Name", "");
    }

    public void setName(String value) {
        mEditor.putString("Name", value).apply();
    }


}