import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

const socket = new SockJS('http://localhost:8080/ws');
const stompClient = new Client({
  webSocketFactory: () => socket,
});

stompClient.onConnect = () => {
  console.log("Conectado ao WebSocket");

  // Inscreve no canal de novos álbuns
  stompClient.subscribe('/topic/newAlbum', (message) => {
    const album = JSON.parse(message.body);
    console.log("Novo álbum recebido:", album);
    // aqui você atualiza a lista de álbuns no front
  });
};

stompClient.activate();
