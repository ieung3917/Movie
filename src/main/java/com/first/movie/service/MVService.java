package com.first.movie.service;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.first.movie.dao.MVDAO;
import com.first.movie.dto.MOVIE;

@Service
public class MVService {

	ModelAndView mav = new ModelAndView();

	@Autowired
	MVDAO mvdao;

	@Autowired
	private HttpSession session;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private BCryptPasswordEncoder pwEnc;

	@Autowired
	private JavaMailSender mailSender;

	public int crl() {

		String str = " ";
		int rank = 1;
		mvdao.movDel();

		try {

			for (int i = 0; i < 20; i++) {
				MOVIE movie = new MOVIE();

				movie.setMovRank(rank);
				Document doc = Jsoup.connect("https://movie.naver.com/movie/sdb/rank/rmovie.naver").get();
				Elements eUrl = doc.select("td.title a");
				str = "https://movie.naver.com";
				str += eUrl.get(i).attr("href");

				System.out.println("[1] src : " + str);
				Document doc2 = Jsoup.connect(str).get();
				Elements specP = doc2.select("dl.info_spec p");
				Elements span0 = doc2.select("dl.info_spec p").get(0).select("span");
				Elements actor = doc2.select("div.people li");

				int sttNum0 = eUrl.get(i).attr("href").indexOf("=");
				String goNum0 = eUrl.get(i).attr("href").substring(sttNum0 + 1, eUrl.get(i).attr("href").length());
				// naver 고유번호
				movie.setMovName(doc2.selectFirst("h3.h_movie a").text()); // 영화이름
				System.out.println("[2] 이름 : " + movie.getMovName());
				movie.setMovGenre(span0.get(0).selectFirst("a").text()); // 영화장르
				try {
					String movDate = span0.get(3).select("a").get(0).text() + span0.get(3).select("a").get(1).text();
					movDate = movDate.replace(" ", "");
					int year = Integer.parseInt(movDate.substring(0, 4));
					int month = Integer.parseInt(movDate.substring(5, 7));
					int day = Integer.parseInt(movDate.substring(8, 10));
					movDate = year + "년" + month + "월" + day + "일";
					movie.setMovDate(movDate);
					System.out.println("date : " + movDate);
					LocalDateTime locTime = LocalDateTime.now();
					LocalDateTime movTime = LocalDateTime.of(year, month, day, 0, 0);
					// 영화개봉일
					if (locTime.isBefore(movTime)) {
						continue;
					}
				} catch (Exception e) {
					String movDate = span0.get(span0.size() - 1).select("a").get(0).text()
							+ span0.get(span0.size() - 1).select("a").get(1).text();
					movDate = movDate.replace(" ", "");
					int year = Integer.parseInt(movDate.substring(0, 4));
					int month = Integer.parseInt(movDate.substring(5, 7));
					int day = Integer.parseInt(movDate.substring(8, 10));
					movDate = year + "년" + month + "월" + day + "일";
					movie.setMovDate(movDate);
					System.out.println("date : " + movDate);
					LocalDateTime locTime = LocalDateTime.now();
					LocalDateTime movTime = LocalDateTime.of(year, month, day, 0, 0);
					// 영화개봉일
					if (locTime.isBefore(movTime)) {
						continue;
					}
				}

				try { // 영화평점
					movie.setMovStar(doc2.select("div.star_score em").get(0).text()
							+ doc2.select("div.star_score em").get(1).text()
							+ doc2.select("div.star_score em").get(2).text()
							+ doc2.select("div.star_score em").get(3).text());
				} catch (Exception e) {
					movie.setMovStar("집계중 입니다.");
				}
				System.out.println("관람가 집계");
				try { // 관람가
					Elements steps = doc2.select("dl.info_spec dt");
					Elements stepAge = doc2.select("dl.info_spec dd");
					if (steps.get(steps.size() - 1).className().toString().equals("step4")) {
						movie.setMovAge(stepAge.get(stepAge.size() - 1).select("a").text());
					} else if (steps.get(steps.size() - 2).className().toString().equals("step4")) {
						movie.setMovAge(stepAge.get(stepAge.size() - 2).select("a").text());
					} else if (steps.get(steps.size() - 3).className().toString().equals("step4")) {
						movie.setMovAge(stepAge.get(stepAge.size() - 3).select("a").text());
					} else {
						movie.setMovAge(" ");
					}
				} catch (Exception e) {
					movie.setMovAge(" ");
				}

				try { // 영화감독이름
					movie.setMovDirector(actor.get(0).selectFirst("a").attr("title"));
				} catch (Exception e) {
					movie.setMovDirector(" ");
				}
				try { // 배우1 이름, 사진
					movie.setMovActor1(actor.get(1).selectFirst("a").attr("title"));
					movie.setMovActorPic1(actor.get(1).selectFirst("a").selectFirst("img").attr("src"));
				} catch (Exception e) {
					movie.setMovActor1(" ");
					movie.setMovActorPic1(" ");
				}
				try { // 배우2 이름,사진
					movie.setMovActor2(actor.get(2).selectFirst("a").attr("title"));
					movie.setMovActorPic2(actor.get(2).selectFirst("a").selectFirst("img").attr("src"));
				} catch (Exception e) {
					movie.setMovActor2(" ");
					movie.setMovActorPic2(" ");
				}
				try { // 배우3 이름,사진
					movie.setMovActor3(actor.get(3).selectFirst("a").attr("title"));
					movie.setMovActorPic3(actor.get(3).selectFirst("a").selectFirst("img").attr("src"));
				} catch (Exception e) {
					movie.setMovActor3(" ");
					movie.setMovActorPic3(" ");
				}
				try { // 배우4 이름,사진
					movie.setMovActor4(actor.get(4).selectFirst("a").attr("title"));
					movie.setMovActorPic4(actor.get(4).selectFirst("a").selectFirst("img").attr("src"));
				} catch (Exception e) {
					movie.setMovActor4(" ");
					movie.setMovActorPic4(" ");
				}
				movie.setMovRunTime(span0.get(2).text()); // 영화 러닝타임
				String text = doc2.select("div.story_area p").toString();
				text = text.substring(18, text.length() - 4);
				movie.setMovContext(text); // 줄거리
				try { // 관객수
					movie.setMovTickets(specP.get(4).text());
				} catch (Exception e) {
					movie.setMovTickets("집계중 입니다.");
				}
				movie.setMovCountry(span0.get(1).select("a").text()); // 제작국가
				String mainPicUrl = "https://movie.naver.com/movie/bi/mi/photoViewPopup.naver?movieCode=" + goNum0;
				Document doc6 = Jsoup.connect(mainPicUrl).get();
				movie.setMovMainPicture(doc6.select("div#page_content img").attr("src"));

				try { // 영화 포스터, 스틸컷 및 티저, 에그점수

					// 영화 포스터
					String cgv = "http://www.cgv.co.kr/search/?query=" + movie.getMovName();
					Document doc4 = Jsoup.connect(cgv).get();
					Element cgUrl = doc4.selectFirst("div.search_wrap a");
					String cgvUrl = "http://www.cgv.co.kr" + cgUrl.attr("href");
					Document doc5 = Jsoup.connect(cgvUrl).get(); // 영화상세페이지

					int stt = cgUrl.attr("href").indexOf("=");
					String mpUrl0 = cgUrl.attr("href").substring(stt + 1, cgUrl.attr("href").length());
					// cgv 고유번호 83203

					String picUrl = str.replace("basic", "photoView");
					System.out.println("[3] picUrl : " + picUrl);
					Document doc3 = Jsoup.connect(picUrl).get(); // 네이버 포토 페이지
					Elements aaa = doc3.select("div.rolling_list li");
					// 스틸컷 6장
					String stillNum[] = new String[6];
					try {
						for (int j = 0; j < 6; j++) {
							String stillData = aaa.get(j).attr("data-json");
							int sttD = stillData.indexOf("imageNid");
							int endD = stillData.indexOf(",", sttD);
							stillNum[j] = stillData.substring(sttD + 10, endD); // 스틸컷 고유번호
						}
						String stillPicUrl = "https://movie.naver.com/movie/bi/mi/photoViewPopup.naver?imageNid="
								+ stillNum[0];
						Document doc7 = Jsoup.connect(stillPicUrl).get();
						movie.setMovPicture1(doc7.select("div#page_content img").attr("src"));
						stillPicUrl = "https://movie.naver.com/movie/bi/mi/photoViewPopup.naver?imageNid="
								+ stillNum[1];
						doc7 = Jsoup.connect(stillPicUrl).get();
						movie.setMovPicture2(doc7.select("div#page_content img").attr("src"));
						stillPicUrl = "https://movie.naver.com/movie/bi/mi/photoViewPopup.naver?imageNid="
								+ stillNum[2];
						doc7 = Jsoup.connect(stillPicUrl).get();
						movie.setMovPicture3(doc7.select("div#page_content img").attr("src"));
						stillPicUrl = "https://movie.naver.com/movie/bi/mi/photoViewPopup.naver?imageNid="
								+ stillNum[3];
						doc7 = Jsoup.connect(stillPicUrl).get();
						movie.setMovPicture4(doc7.select("div#page_content img").attr("src"));
						stillPicUrl = "https://movie.naver.com/movie/bi/mi/photoViewPopup.naver?imageNid="
								+ stillNum[4];
						doc7 = Jsoup.connect(stillPicUrl).get();
						movie.setMovPicture5(doc7.select("div#page_content img").attr("src"));
						stillPicUrl = "https://movie.naver.com/movie/bi/mi/photoViewPopup.naver?imageNid="
								+ stillNum[5];
						doc7 = Jsoup.connect(stillPicUrl).get();
						movie.setMovPicture6(doc7.select("div#page_content img").attr("src"));

						// 스틸컷 적용 완료
					} catch (Exception e) {

						if (movie.getMovPicture1() == null) {
							movie.setMovPicture1(" ");
						}
						if (movie.getMovPicture2() == null) {
							movie.setMovPicture2(" ");
						}
						if (movie.getMovPicture3() == null) {
							movie.setMovPicture3(" ");
						}
						if (movie.getMovPicture4() == null) {
							movie.setMovPicture4(" ");
						}
						if (movie.getMovPicture5() == null) {
							movie.setMovPicture5(" ");
						}
						if (movie.getMovPicture6() == null) {
							movie.setMovPicture6(" ");
						}

					}

					int mpLen = mpUrl0.length();
					Element cgMp4 = doc5.selectFirst("div.sect-trailer img");
					String mpUrl1 = cgMp4.attr("src");
					// https://img.cgv.co.kr/Movie/Thumbnail/Trailer/83203/83203210449_1024.jpg
					int sttUr = mpUrl1.indexOf(mpUrl0);
					int endUr = mpUrl1.indexOf("_");
					String mpUrl2 = mpUrl1.substring(sttUr + mpLen + mpLen + 1, endUr);
					// 또다른 고유번호 210449
					String mpUrl3 = "http://h.vod.cgv.co.kr:80/vodCGVa/" + mpUrl0 + "/" + mpUrl0 + "_" + mpUrl2
							+ "_1200_128_960_540.mp4";
					movie.setMovTeaserUrl(mpUrl3);
					Element cgEgg = doc5.selectFirst("div.egg_point");
					movie.setMovPeScore(cgEgg.select("span.percent").get(0).text());
					// 에그점수
					movie.setMovGeScore(cgEgg.select("span.percent").get(1).text());
					// 골든 에그점수
				} catch (Exception e) {
					movie.setMovTeaserUrl(" ");
					movie.setMovPeScore("집계중 입니다.");
					movie.setMovGeScore("집계중 입니다.");

					if (movie.getMovPicture1() == null) {
						movie.setMovPicture1(" ");
					}
					if (movie.getMovPicture2() == null) {
						movie.setMovPicture2(" ");
					}
					if (movie.getMovPicture3() == null) {
						movie.setMovPicture3(" ");
					}
					if (movie.getMovPicture4() == null) {
						movie.setMovPicture4(" ");
					}
					if (movie.getMovPicture5() == null) {
						movie.setMovPicture5(" ");
					}
					if (movie.getMovPicture6() == null) {
						movie.setMovPicture6(" ");
					}

				}

				int result = mvdao.movieData(movie);
				rank++;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

}
