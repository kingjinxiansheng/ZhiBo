package com.example.lenovo.zhibo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String rtmpUrl = "rtmp://3891.livepush.myqcloud.com/live/3891_test001_A?bizid=3891&txSecret=70f6e3c168c95838cc1113410630f572&txTime=5C2A3CFF";
    String flvUrl = "rtmp://3891.liveplay.myqcloud.com/live/3891_test001_A?bizid=3891&txSecret=70f6e3c168c95838cc1113410630f572&txTime=5C2A3CFF";
    String BrtmpUrl = "rtmp://3891.livepush.myqcloud.com/live/3891_test001_B?bizid=3891&txSecret=c955e184a0aac1ba071d1980e7a42683&txTime=5C2A3CFF";
    String BflvUrl = "rtmp://3891.liveplay.myqcloud.com/live/3891_test001_B?bizid=3891&txSecret=c955e184a0aac1ba071d1980e7a42683&txTime=5C2A3CFF";
    private Button kaizhibo;
    private Button kanzhibo;
    private TXCloudVideoView video_view;
    private TXLivePushConfig mLivePushConfig;
    private TXLivePusher mLivePusher;
    private TXLivePlayer mLivePlayer;
    private Button Bkaizhibo;
    private Button Bkanzhibo;
    private TXCloudVideoView Bvideo_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //这是实现推流，开直播
        mLivePusher = new TXLivePusher(this);
        mLivePushConfig = new TXLivePushConfig();
        mLivePusher.setConfig(mLivePushConfig);
        //这个是实现拉流观看视频，直播
        //创建player对象
        mLivePlayer = new TXLivePlayer(this);
        //关键player对象与界面view

        initView();
    }

    private void initView() {
        kaizhibo = (Button) findViewById(R.id.kaizhibo);
        kanzhibo = (Button) findViewById(R.id.kanzhibo);
        video_view = (TXCloudVideoView) findViewById(R.id.video_view);
        kaizhibo.setOnClickListener(this);
        kanzhibo.setOnClickListener(this);
        Bkaizhibo = (Button) findViewById(R.id.Bkaizhibo);
        Bkaizhibo.setOnClickListener(this);
        Bkanzhibo = (Button) findViewById(R.id.Bkanzhibo);
        Bkanzhibo.setOnClickListener(this);
        Bvideo_view = (TXCloudVideoView) findViewById(R.id.Bvideo_view);
        Bvideo_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kaizhibo:
                mLivePusher.startCameraPreview(Bvideo_view);
                mLivePusher.startPusher(rtmpUrl);
                break;
            case R.id.kanzhibo:
                mLivePlayer.setPlayerView(video_view);
                mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_LIVE_RTMP);
                break;
            case R.id.Bkaizhibo:
                mLivePusher.startCameraPreview(Bvideo_view);
                mLivePusher.startPusher(BrtmpUrl);
                break;
            case R.id.Bkanzhibo:
                mLivePlayer.setPlayerView(video_view);
                mLivePlayer.startPlay(BflvUrl, TXLivePlayer.PLAY_TYPE_LIVE_RTMP);
                break;
        }
    }
}
