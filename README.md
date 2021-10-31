<h1> 
  MediaPlayer
</h1>

<h2>
  Функционал
</h2>
<ol>
  <li>Возможность проигрывать аудиофайлов и видеофайлов</li>
  <li>Плейер: плей/пауза, следующая/предыдущая композиция, промотка вперед/назад на 10 сек для аудио и видео</li>
  <li>Следующая/предыдущая композиция(видео) реализована с помощью свайпа</li>
  <li>Реализован полноэкранный режим для видео</li>
  <li>Реализовано прослушивание музыки в фоновом режиме с выводом сообщения/нотификации</li>
</ol>

<h2>
  Ссылки на элементы проекта
</h2>


  - [MainActivity](./java/com/example/mediaplayer/MainActivity.java)
  - [PageFragment](./java/com/example/mediaplayer/PageFragment.java) - родительский класс для PageAudioFragment.java и PageVideoFragment.java
  - [PageAudioFragment](./java/com/example/mediaplayer/PageAudioFragment.java) - класс для добавления фрагмента аудио
  - [PageVideoFragment](./java/com/example/mediaplayer/PageVideoFragment.java) - класс для добавления фрагмента видео
  - [PageAdapter](./java/com/example/mediaplayer/PageAdapter.java) - адаптер для отображения фрагментов
  - [MyMediaController](./java/com/example/mediaplayer/MyMediaController.java) - кастомный MediaController для видео
  - [FullScreenActivity](./java/com/example/mediaplayer/FullScreenActivity.java) - активити для полноэкранного режима
  - [MediaService](./java/com/example/mediaplayer/MediaService.java) - сервис для музыки
  - [MusicNotification](./java/com/example/mediaplayer/MusicNotification.java) - для вывода нотификации
