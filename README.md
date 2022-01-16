# RoseClimate
![Hi](readmeimg.png)
NWHacks 2022 project for positive Climate Change news and volunteer enablement

Made by:
[Gurjot](https://github.com/goodfeller)
[Kevin](https://github.com/kevinlinxc)
[Richard](https://github.com/rrhan0)

# Premise
Our app does two things. First, it shows you positive news about climate change. This is to 
shed light on all the communities rallying across the world, working away to reduce the effects 
of climate change on humans. Second, the app aggregates volunteering opportunities near you in 
order to empower users who want to help but don't know how. In sum, this app is meant to raise 
morale of people around the world in regards to the climate crisis, while also channeling that 
morale into actual action. 

# Implementation
- The app is an Android app, and it was chosen mostly because we are all familiar with Java.
- We poll climate-specific RSS feeds to gather articles about climate change.
- We make API calls to IBM Watson's Natural Language Understanding API to see if articles have 
  positive sentiment.
- News is displayed in a RecyclerView, sorted by latest, and clicking on an article opens it in a 
  built-in browser
- Volunteering is displayed in another tab. Ideally, volunteering would also be an RSS feed or 
  API call, but the only available API, "[VolunteerMatch](https://solutions.volunteermatch.org/product/compare)" costs 500 dollars minimum. So, we 
  hard-coded the volunteering organizations temporarily. Our original plan was to use 
  BeautifulSoup/jsoup to read [this Vancouver volunteering site](http://www.canadian-universities.net/Volunteer/Environment-British_Columbia-Vancouver.html) as a middle-ground, but we ran 
  out of time
- Currently, loading news takes a while as we need to check every article with the API. We've 
  iplemented a cache so that it is almost instantly loaded the second time the news tab is 
  visited. This whole problem would be solved in a scaled-up case by using a central database that is updated independently 
  of the actual app.

# Full Pitch
Even before the global pandemic, the news was depressing. 
Climate change is affecting people all around the world, and we’re constantly told that it’s only going to get worse. 
Wouldn’t it be nice to have some good news for a change? 
We’ve made an app that displays good news about Climate Change from around the world. 
Don’t get us wrong- being ignorant about the actual suffering caused by climate change is not the goal. 
The goal is to bring attention to all the volunteering and positive action taking place around the world that gets overshadowed by negativity. 
This also leads well into the second half of our app: volunteering. 
People often dismiss the role of personal responsibility in the fight against climate change, 
citing that big companies are causing it and so we should not feel guilted into changing our lifestyle. 
The thing is, if the building is on fire, there’s probably something you could do to help, even if you can’t put out the whole fire, 
and seeing other people helping is important, because it makes you realize that you could be helping too. 
That is why our app also uses volunteering APIs to bring awareness to volunteering opportunities in your community. 
The app first empowers you by showing the good happening in the world, and then provides you with the means to volunteer at a moment’s notice. 
You’ll be happier, the environmental organizations in need of volunteers will be happier, and our planet will be happier.
