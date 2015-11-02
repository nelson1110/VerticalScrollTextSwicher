import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nelson on 2015/10/22.
 * E-mail:nelson2014@qq.com
 * What:自定义里程表式text滚动
 */
public class VerticalScrollTextSwicher extends TextSwitcher {
    private List<String> texts;
    private int TEXT_DURATION = 1000;
    private int ANIM_DURATION = 800;
    private int TEXT_SIZE = 16;
    private int DEFAULT_TEXT_COLOR = Color.parseColor("#FF000000");
    private int i = 0;
    private Handler handler;

    public VerticalScrollTextSwicher(Context context) {
        super(context, null);
    }

    public VerticalScrollTextSwicher(Context context, AttributeSet attrs) {
        super(context, attrs);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                showNext();
                setCurrentText(msg.obj.toString());


            }
        };
    }

    @Override
    public void setOutAnimation(Animation outAnimation) {
        if (outAnimation == null) {
            outAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, -1);
            outAnimation.setDuration(ANIM_DURATION);
            super.setOutAnimation(outAnimation);
        }
        super.setOutAnimation(outAnimation);
    }


    public void start() {
        setFactory(null);
        setInAnimation(null);
        setOutAnimation(null);
        Timer timer = new Timer("start");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                TextView textView = (TextView) getCurrentView();
                if (textView == null) {
                } else {
                    Message message = Message.obtain();
                    message.obj = texts.get(i % texts.size()).toString();
                    handler.sendMessage(message);
                }
                i++;
            }
        }, 0, TEXT_DURATION);
    }

    @Override
    public void setInAnimation(Animation inAnimation) {
        if (inAnimation == null) {
            inAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_PARENT, 0);
            inAnimation.setDuration(ANIM_DURATION);
            inAnimation.setFillAfter(true);
            super.setInAnimation(inAnimation);
        }
        super.setInAnimation(inAnimation);
    }

    public void setTexts(List<String> texts) {
        if (texts != null) {
            this.texts = texts;
        }
    }

    @Override
    public void setFactory(ViewFactory factory) {
        if (factory == null) {
            factory = new ViewFactory() {
                @Override
                public View makeView() {
                    TextView textView = new TextView(getContext());
                    textView.setTextColor(DEFAULT_TEXT_COLOR);
                    textView.setTextSize(TEXT_SIZE);
                    textView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                    textView.setGravity(Gravity.CENTER);
                    return textView;
                }
            };
            super.setFactory(factory);
        }
    }

    public void setContentTextColor(int color) {
        this.DEFAULT_TEXT_COLOR = color;
    }

    public void setContentTextSize(int textSize) {
        this.TEXT_SIZE = textSize;
    }

    public void setTextDuration(int duration) {
        this.TEXT_DURATION = duration;
    }
    public void setAnimDuration(int duration) {
        this.ANIM_DURATION = duration;
    }

}
