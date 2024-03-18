
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

export interface IArticle {
    id: number;
    source: ISource;
    author: string;
    title: string;
    description: string;
    url: string;
    urlToImage: string;
    publishedAt: string;
    content: string;
}

export interface ISource {
    id: string | null;
    name: string;
}
