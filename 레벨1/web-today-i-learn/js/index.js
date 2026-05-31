// DOM 요소 선택
const tilForm = document.querySelector('#til-form');
const tilList = document.querySelector('#til-list');

// TIL 등록 이벤트 리스너
tilForm.addEventListener('submit', function (event) {
  // 폼 제출 시 페이지 새로고침 방지
  event.preventDefault();

  // 입력값 가져오기
  const date = document.querySelector('#til-date').value;
  const title = document.querySelector('#til-title').value;
  const content = document.querySelector('#til-content').value;

  // 새로운 TIL 아이템(article) 생성
  const newTilItem = document.createElement('article');
  newTilItem.classList.add('til-item');

  // 내부 구조 구성 (템플릿 리터럴 활용)
  newTilItem.innerHTML = `
    <time>${date}</time>
    <h3>${title}</h3>
    <p>${content}</p>
  `;

  // 목록의 가장 앞에 추가
  tilList.prepend(newTilItem);

  // 폼 초기화
  tilForm.reset();

  alert('오늘의 배움을 기록했습니다!');
});