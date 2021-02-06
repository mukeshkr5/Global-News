package com.mkr.globalnews.repository.mock

import com.mkr.globalnews.modal.News
import com.mkr.globalnews.modal.NewsResponse
import com.mkr.globalnews.modal.NewsSource
import com.mkr.globalnews.repository.NewsRepository
import com.mkr.globalnews.utils.Resource

class MockNewsRepository : NewsRepository {

    override suspend fun getNewsList(page: Int, country: String): Resource<NewsResponse> {
        return Resource.success(
            NewsResponse(
                "ok",
                38,
                arrayListOf(
                    News(
                        NewsSource("bbc-news", "BBC News"),
                        "BBC News",
                        "Biden: 'Erratic' Trump should not get intelligence briefings",
                        "President Biden says Mr Trump should not have classified reports as \"he might slip and say something\".",
                        "http://www.bbc.co.uk/news/world-us-canada-55960368",
                        "https://ichef.bbci.co.uk/images/ic/400xn/p096727y.jpg",
                        "2021-02-06T03:37:22.1439003Z",
                        "US President Joe Biden hinted he could deny Mr Trump a courtesy usually afforded to former presidents because of \"the fact that he might slip and say something\"."
                    ),
                    News(
                        NewsSource("bbc-news", "BBC News"),
                        "BBC News",
                        "Biden leaves Republicans behind to fast track \$1.9tn bill",
                        "The White House brushes off an ex-Biden adviser's warning the package could destabilise the economy.",
                        "http://www.bbc.co.uk/news/world-us-canada-55960364",
                        "https://ichef.bbci.co.uk/news/1024/branded_news/7660/production/_116840303_065545361.jpg",
                        "2021-02-06T03:07:21.151801Z",
                        "media captionBiden pledges to \"act fast\" on stimulus\n" +
                                "US President Joe Biden is forging ahead with plans to ram through a \$1.9tn (£1.4tn) relief bill without Republican support after disappointing jo… [+4452 chars]" +
                                "media captionBiden pledges to \"act fast\" on stimulus\n" +
                                "US President Joe Biden is forging ahead with plans to ram through a \$1.9tn (£1.4tn) relief bill without Republican support after disappointing jo… [+4452 chars]"
                    ),
                    News(
                        NewsSource("bbc-news", "BBC News"),
                        "BBC News",
                        "UK expelled Chinese journalists 'working as spies'",
                        "The departures came after suspicions their work in the media was cover for intelligence gathering.",
                        "http://www.bbc.co.uk/news/uk-55956245",
                        "https://ichef.bbci.co.uk/news/1024/branded_news/1303B/production/_116838877_gettyimages-1285318170.jpg",
                        "2021-02-06T02:52:26.8478191Z",
                        "By Gordon CoreraBBC News\n" +
                                "image copyrightGetty Images\n" +
                                "Three journalists who were allegedly working as spies for China were asked to leave the UK last year. \n" +
                                "Their departure, first reported by the D… [+1512 chars]"
                    ),
                    News(
                        NewsSource("bbc-news", "BBC News"),
                        "BBC News",
                        "Swiss women only got the vote 50 years ago. Why?",
                        "Women in Switzerland were finally granted the right to vote after a referendum in February 1971.",
                        "http://www.bbc.co.uk/news/world-europe-55950920",
                        "https://ichef.bbci.co.uk/images/ic/400xn/p096617s.jpg",
                        "2021-02-06T02:52:25.034237Z",
                        "Women in Switzerland are remembering a key moment in history this weekend - the 50th anniversary of finally being granted the right to vote. \n" +
                                "Switzerland lagged far behind its neighbours Italy, Fran… [+162 chars]"
                    ),
                    News(
                        NewsSource("bbc-news", "BBC News"),
                        "BBC News",
                        "ICC rules it has jurisdiction over West Bank and Gaza 'abuses'",
                        "The international court rules it has jurisdiction to investigate alleged crimes in the West Bank and Gaza.",
                        "http://www.bbc.co.uk/news/world-middle-east-55956771",
                        "https://ichef.bbci.co.uk/news/1024/branded_news/13EB9/production/_116839518_f7681759-2fa1-4756-9d65-00b851befeda.jpg",
                        "2021-02-06T02:52:23.1284612Z",
                        "image captionPalestinians have been calling for the ICC to investigate alleged abuses for years\n" +
                                "The International Criminal Court ruled on Friday that it has jurisdiction over war crimes and atrociti… [+2764 chars]"
                    )
                )
            )
        )
    }

    override suspend fun getNewsListByCategory(
        category: String,
        country: String
    ): Resource<NewsResponse> {
        return Resource.success(
            NewsResponse(
                "ok",
                38,
                arrayListOf(
                    News(
                        NewsSource("bbc-news", "BBC News"),
                        "BBC News",
                        "Biden: 'Erratic' Trump should not get intelligence briefings",
                        "President Biden says Mr Trump should not have classified reports as \"he might slip and say something\".",
                        "http://www.bbc.co.uk/news/world-us-canada-55960368",
                        "https://ichef.bbci.co.uk/images/ic/400xn/p096727y.jpg",
                        "2021-02-06T03:37:22.1439003Z",
                        "US President Joe Biden hinted he could deny Mr Trump a courtesy usually afforded to former presidents because of \"the fact that he might slip and say something\"."
                    ),
                    News(
                        NewsSource("bbc-news", "BBC News"),
                        "BBC News",
                        "Biden leaves Republicans behind to fast track \$1.9tn bill",
                        "The White House brushes off an ex-Biden adviser's warning the package could destabilise the economy.",
                        "http://www.bbc.co.uk/news/world-us-canada-55960364",
                        "https://ichef.bbci.co.uk/news/1024/branded_news/7660/production/_116840303_065545361.jpg",
                        "2021-02-06T03:07:21.151801Z",
                        "media captionBiden pledges to \"act fast\" on stimulus\n" +
                                "US President Joe Biden is forging ahead with plans to ram through a \$1.9tn (£1.4tn) relief bill without Republican support after disappointing jo… [+4452 chars]" +
                                "media captionBiden pledges to \"act fast\" on stimulus\n" +
                                "US President Joe Biden is forging ahead with plans to ram through a \$1.9tn (£1.4tn) relief bill without Republican support after disappointing jo… [+4452 chars]"
                    ),
                    News(
                        NewsSource("bbc-news", "BBC News"),
                        "BBC News",
                        "UK expelled Chinese journalists 'working as spies'",
                        "The departures came after suspicions their work in the media was cover for intelligence gathering.",
                        "http://www.bbc.co.uk/news/uk-55956245",
                        "https://ichef.bbci.co.uk/news/1024/branded_news/1303B/production/_116838877_gettyimages-1285318170.jpg",
                        "2021-02-06T02:52:26.8478191Z",
                        "By Gordon CoreraBBC News\n" +
                                "image copyrightGetty Images\n" +
                                "Three journalists who were allegedly working as spies for China were asked to leave the UK last year. \n" +
                                "Their departure, first reported by the D… [+1512 chars]"
                    ),
                    News(
                        NewsSource("bbc-news", "BBC News"),
                        "BBC News",
                        "Swiss women only got the vote 50 years ago. Why?",
                        "Women in Switzerland were finally granted the right to vote after a referendum in February 1971.",
                        "http://www.bbc.co.uk/news/world-europe-55950920",
                        "https://ichef.bbci.co.uk/images/ic/400xn/p096617s.jpg",
                        "2021-02-06T02:52:25.034237Z",
                        "Women in Switzerland are remembering a key moment in history this weekend - the 50th anniversary of finally being granted the right to vote. \n" +
                                "Switzerland lagged far behind its neighbours Italy, Fran… [+162 chars]"
                    ),
                    News(
                        NewsSource("bbc-news", "BBC News"),
                        "BBC News",
                        "ICC rules it has jurisdiction over West Bank and Gaza 'abuses'",
                        "The international court rules it has jurisdiction to investigate alleged crimes in the West Bank and Gaza.",
                        "http://www.bbc.co.uk/news/world-middle-east-55956771",
                        "https://ichef.bbci.co.uk/news/1024/branded_news/13EB9/production/_116839518_f7681759-2fa1-4756-9d65-00b851befeda.jpg",
                        "2021-02-06T02:52:23.1284612Z",
                        "image captionPalestinians have been calling for the ICC to investigate alleged abuses for years\n" +
                                "The International Criminal Court ruled on Friday that it has jurisdiction over war crimes and atrociti… [+2764 chars]"
                    ),
                    News(
                        NewsSource("bbc-news", "BBC News"),
                        "BBC News",
                        "ICC rules it has jurisdiction over West Bank and Gaza 'abuses'",
                        "The international court rules it has jurisdiction to investigate alleged crimes in the West Bank and Gaza.",
                        "http://www.bbc.co.uk/news/world-middle-east-55956771",
                        "https://ichef.bbci.co.uk/news/1024/branded_news/13EB9/production/_116839518_f7681759-2fa1-4756-9d65-00b851befeda.jpg",
                        "2021-02-06T02:52:23.1284612Z",
                        "image captionPalestinians have been calling for the ICC to investigate alleged abuses for years\n" +
                                "The International Criminal Court ruled on Friday that it has jurisdiction over war crimes and atrociti… [+2764 chars]"
                    ),
                    News(
                        NewsSource("bbc-news", "BBC News"),
                        "BBC News",
                        "ICC rules it has jurisdiction over West Bank and Gaza 'abuses'",
                        "The international court rules it has jurisdiction to investigate alleged crimes in the West Bank and Gaza.",
                        "http://www.bbc.co.uk/news/world-middle-east-55956771",
                        "https://ichef.bbci.co.uk/news/1024/branded_news/13EB9/production/_116839518_f7681759-2fa1-4756-9d65-00b851befeda.jpg",
                        "2021-02-06T02:52:23.1284612Z",
                        "image captionPalestinians have been calling for the ICC to investigate alleged abuses for years\n" +
                                "The International Criminal Court ruled on Friday that it has jurisdiction over war crimes and atrociti… [+2764 chars]"
                    ),
                    News(
                        NewsSource("bbc-news", "BBC News"),
                        "BBC News",
                        "ICC rules it has jurisdiction over West Bank and Gaza 'abuses'",
                        "The international court rules it has jurisdiction to investigate alleged crimes in the West Bank and Gaza.",
                        "http://www.bbc.co.uk/news/world-middle-east-55956771",
                        "https://ichef.bbci.co.uk/news/1024/branded_news/13EB9/production/_116839518_f7681759-2fa1-4756-9d65-00b851befeda.jpg",
                        "2021-02-06T02:52:23.1284612Z",
                        "image captionPalestinians have been calling for the ICC to investigate alleged abuses for years\n" +
                                "The International Criminal Court ruled on Friday that it has jurisdiction over war crimes and atrociti… [+2764 chars]"
                    ),
                    News(
                        NewsSource("bbc-news", "BBC News"),
                        "BBC News",
                        "ICC rules it has jurisdiction over West Bank and Gaza 'abuses'",
                        "The international court rules it has jurisdiction to investigate alleged crimes in the West Bank and Gaza.",
                        "http://www.bbc.co.uk/news/world-middle-east-55956771",
                        "https://ichef.bbci.co.uk/news/1024/branded_news/13EB9/production/_116839518_f7681759-2fa1-4756-9d65-00b851befeda.jpg",
                        "2021-02-06T02:52:23.1284612Z",
                        "image captionPalestinians have been calling for the ICC to investigate alleged abuses for years\n" +
                                "The International Criminal Court ruled on Friday that it has jurisdiction over war crimes and atrociti… [+2764 chars]"
                    )
                )
            )
        )
    }
}