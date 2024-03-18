
// create interface for Iarticle using  {
//     "id": 1,
//     "source": {
//       "id": null,
//       "name": "Investor's Business Daily"
//     },
//     "author": "Investor's Business Daily",
//     "title": "Stock Market Today: Dow Jones Rises Ahead Of Key Inflation Data; Nvidia, Tesla Extend Losses - Investor's Business Daily",
//     "description": null,
//     "url": "https://www.investors.com/market-trend/stock-market-today/dow-jones-sp500-nasdaq-ppi-nvidia-nvda-stock-tesla-tsla-stock/",
//     "urlToImage": null,
//     "publishedAt": "2024-03-14T12:25:33Z",
//     "content": null
//   }

/**
 * Represents an article.
 */
export interface IArticle {
    /**
     * The unique identifier of the article.
     */
    id: number;

    /**
     * The source of the article.
     */
    source: ISource;

    /**
     * The author of the article.
     */
    author: string;

    /**
     * The title of the article.
     */
    title: string;

    /**
     * The description of the article.
     */
    description: string;

    /**
     * The URL of the article.
     */
    url: string;

    /**
     * The URL to the image associated with the article.
     */
    urlToImage: string;

    /**
     * The date and time when the article was published.
     */
    publishedAt: string;

    /**
     * The content of the article.
     */
    content: string;
}

export interface ISource {
    id: string | null;
    name: string;
}
