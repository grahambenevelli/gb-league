package com.gbleague.web.controller.newsfeed;

import com.gbleague.league.model.newsfeed.PojoNewsFeed;
import com.gbleague.web.dto.newsfeed.NewsFeedDTO;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/newsfeed")
public class NewsFeedController {


	@RequestMapping(value="/", method = RequestMethod.GET)
	@ResponseBody
	public List<NewsFeedDTO> getNewsFeeds() {
		List<PojoNewsFeed> newsFeeds = Lists.newArrayList();
		newsFeeds.add(new PojoNewsFeed(1, "http://blog.chron.com/ultimatetexans/files/2013/05/20130105_TexansPlayoffs_KAW_026.jpg", "What IDP players should you go after", "I would go JJ Watt first."));
		newsFeeds.add(new PojoNewsFeed(2, "http://www.fantasywired.com/wp-content/uploads/2013/08/aaron-rodgers-packers.jpg", "QB Rankings", "Rodgers #1"));
		newsFeeds.add(new PojoNewsFeed(3, "http://thenypost.files.wordpress.com/2014/09/rice10.jpg", "How do you replace Ray Rice or Peterson", "Hope and pray"));

		List<NewsFeedDTO> newsFeedDTOs = Lists.newArrayList();
		for (PojoNewsFeed newsFeed : newsFeeds) {
			newsFeedDTOs.add(new NewsFeedDTO(newsFeed));
		}

		return newsFeedDTOs;
	}

}
