package vo.cvcompany.com.loadnhac;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG =MainActivity.class.getSimpleName() ;
    @BindView(R.id.seekOne)
    SeekBar seekOne;
    @BindView(R.id.seekTwo)
    SeekBar seekTwo;
    @BindView(R.id.seekThree)
    SeekBar seekThree;
    @BindView(R.id.imgPlay)
    ImageView imgPlay;
    private String choose ="";
   private String i="",ii="",iii="";
    private CountDownTimer countDownTimer;
    @BindView(R.id.cbOne)
    CheckBox cbOne;
    @BindView(R.id.cbTwo)
    CheckBox cbTwo;
    @BindView(R.id.cbThree)
    CheckBox cbThree;
    private boolean checkOne=false,checkTwo=false,checkThree=false;
    @BindView(R.id.txtMark)
    TextView txtMark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        seekThree.setEnabled(false);
        seekTwo.setEnabled(false);
        seekOne.setEnabled(false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("seekOne", seekOne.getProgress());
        outState.putInt("seekTwo", seekTwo.getProgress());
        outState.putInt("seekThree", seekThree.getProgress());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @OnClick(R.id.imgPlay)
    public void imgPlay() {

            imgPlay.setVisibility(View.GONE);
            cbTwo.setEnabled(false);
            cbOne.setEnabled(false);
            cbThree.setEnabled(false);
            countDownTimer = new CountDownTimer(10000, 50) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int time = 3;
                    int a = new Random().nextInt(time);
                    int b = new Random().nextInt(time);
                    int c = new Random().nextInt(time);
                    seekOne.setProgress(seekOne.getProgress() + a);
                    seekTwo.setProgress(seekTwo.getProgress() + b);
                    seekThree.setProgress(seekThree.getProgress() + c);

                    if(seekOne.getProgress()>=seekOne.getMax()){
                        if(!checkOne)
                        if(i==""){
                            i="Seek One Win";
                            checkOne  =true;
                        }else if(ii==""){
                            ii="Seek One 2nd";
                            checkOne  =true;
                        }else if(iii==""){
                            iii="Seek One 3rd";
                            checkOne  =true;
                        }
                    }

                    if(seekTwo.getProgress()>=seekTwo.getMax()){
                        if(!checkTwo)
                            if(i==""){
                                i="Seek Two Win";
                                checkTwo  =true;
                            }else if(ii==""){
                                ii="Seek Two 2nd";
                                checkTwo  =true;
                            }else if(iii==""){
                                iii="Seek Two 3rd";
                                checkTwo  =true;
                            }
                    }

                    if(seekThree.getProgress()>=seekThree.getMax()){
                        if(!checkThree)
                            if(i==""){
                                i="Seek Three Win";
                                checkThree  =true;
                            }else if(ii==""){
                                ii="Seek Three 2nd";
                                checkThree  =true;
                            }else if(iii==""){
                                iii="Seek Three 3rd";
                                checkThree  =true;
                            }
                    }
                }

                @Override
                public void onFinish() {
                    processWhenFinish();
                    Toast.makeText(MainActivity.this, ""+i+" "+ii+" "+iii, Toast.LENGTH_SHORT).show();
                    setMark();
                     i="";ii="";iii="";
                    setCheckBox();
                }
            };
            countDownTimer.start();
    }

    private void processWhenFinish() {
        imgPlay.setVisibility(View.VISIBLE);
        seekThree.setProgress(0);
        seekTwo.setProgress(0);
        seekOne.setProgress(0);
        checkOne=false;
        checkTwo=false;
        checkThree=false;
    }

    private void setMark() {
        int mark= Integer.parseInt( txtMark.getText().toString().trim());
        if(cbOne.isChecked()){
            mark -=5;
            if(i.equals("Seek One Win")){
                mark+=15;
            }
        }

        if(cbTwo.isChecked()){
            mark -=5;
            if(i.equals("Seek Two Win")){
                mark+=15;
            }
        }

        if(cbThree.isChecked()){
            mark -=5;
            if(i.equals("Seek Three Win")){
                mark+=15;
            }
        }

        txtMark.setText(""+mark);
    }

    public void setCheckBox(){
        cbThree.setEnabled(true);
        cbTwo.setEnabled(true);
        cbOne.setEnabled(true);
        cbThree.setChecked(false);
        cbOne.setChecked(false);
        cbTwo.setChecked(false);
    }
}
