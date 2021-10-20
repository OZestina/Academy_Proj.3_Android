package com.app.gitsin;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class StreakCrawling {

    //가장 최근 로그인 날짜 (streak기준일)
    private String streakCheckStart;
    //맥시멈 streak & counting용 변수
    private int maxStreak;
    //현재 streak
    private int streakToday;
    //오늘 contribute 여부
    private int todayCount;

    public StreakCrawling (String user) {
        new Thread(){
            @Override
            public void run() {
                try {
                    //크롤링할 웹사이트 링크 & 크롤링 시작
                    Document doc = Jsoup.connect("https://github.com/"+user).get();
                    Elements els = doc.select("rect.ContributionCalendar-day");

                    //어제날짜 & 오늘날짜 구하기 (변수 yesterday & today)
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar date = new GregorianCalendar();
                    String today = format.format(date.getTime());
                    date.add(Calendar.DATE, -1);
                    String yesterday = format.format(date.getTime());

                    //streak 체크
                    for(Element e : els) {
                        if (!e.getElementsByAttribute("data-date").attr("data-count").equals("0")) {	//contribution이 있으면
                            streakToday += 1;
                            if (e.getElementsByAttribute("data-date").attr("data-date").equals(today)) {
                                todayCount = 1;
                                if (streakToday > maxStreak) { maxStreak = streakToday; }				//최대 streak 업데이트 (현재 진행 streak)
                                break;
                            }
                        } else {																		//contribution이 없으면
                            if (streakToday > maxStreak) { maxStreak = streakToday; }					//최대 streak 업데이트
                            streakToday = 0;
                            if (e.getElementsByAttribute("data-date").attr("data-date").equals(today)) { break; }
                        }
                    }
                    streakCheckStart = today;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();  //end of Thread
    }//end of constructor

    public String getStreakCheckStart() {
        return streakCheckStart;
    }

    public int getMaxStreak() {
        return maxStreak;
    }

    public int getStreakToday() {
        return streakToday;
    }

    public int getTodayCount() {
        return todayCount;
    }
}
