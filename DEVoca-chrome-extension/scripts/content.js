// 콘텐츠 스크립트
// - 웹페이지 컨텍스트에서 JS 실행
// - 표준 DOM을 사용해 방문한 웹페이지 세부 정보를 읽고, 변경하고, 정보를 상위 확장 프로그램에 전달.
//
// author: cheesecat47 <cheese47@gmail.com>

document.querySelector('#searchBtn').addEventListener('click', () => {
    const searchInput = document.querySelector('#searchInput').value
    if (searchInput === '') {
        return
    }

    const url = `https://i10d112.p.ssafy.io/devoca/search/word/${searchInput}/0`

    axios.get(url)
        .then((response) => {
            const searchResults = response.data
            console.log(searchResults)

            const tabContent = document.querySelector('#tabContent')
            tabContent.innerHTML = null
            if (searchResults.length == 0) {
                tabContent.textContent = "흐음..🧐 다른 단어를 찾아볼까요?"
                return
            }

            tabContent.classList.remove('text-center')
            for (let item of searchResults.splice(0, 3)) {
                const card = document.createElement('div')
                card.classList.add('card', 'card-compact', 'bg-base-100', 'dark:bg-base-100', 'shadow-xl', 'mb-4', 'p-2')

                const cardBody = document.createElement('div')
                cardBody.classList.add('card-body')

                // 단어 (영문)
                const wordNameEn = document.createElement('h2')
                wordNameEn.classList.add('card-title', 'text-base')
                wordNameEn.textContent = item['wordNameEn']
                cardBody.appendChild(wordNameEn)

                // 단어 (국문)
                const wordNameKr = document.createElement('span')
                wordNameKr.classList.add('text-gray-400', 'text-xs')
                wordNameKr.textContent = item['wordNameKr']
                cardBody.appendChild(wordNameKr)

                cardBody.appendChild(document.createElement('hr'))

                // 요약
                const wordSumm = document.createElement('p')
                wordSumm.classList.add('text-sm')
                wordSumm.textContent = item['wordSumm']
                cardBody.appendChild(wordSumm)

                // 카테고리
                const categories = document.createElement('div')
                categories.classList.add('flex', 'justify-start', 'gap-1', 'mt-2', 'text-xs')
                for (let categoryName of item['wordCategory']) {
                    const category = document.createElement('span')
                    category.textContent = `#${categoryName}`
                    categories.appendChild(category)
                }
                cardBody.appendChild(categories)

                card.appendChild(cardBody)

                tabContent.appendChild(card)
            }
        })
});

document.querySelector('#moreOnDEVocaBtn').addEventListener('click', () => {
    let url = 'https://i10d112.p.ssafy.io'

    const searchInput = document.querySelector('#searchInput').value
    if (searchInput === '') {
        url += '/word/list'
    } else {
        url += `/search/result?q=${searchInput}`
    }

    window.open(url, '_blank');
})