package com.github.hiuchida.kabusapi.wsserver;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.github.hiuchida.kabusapi.client_ex.pushapi.BoardBean;
import com.github.hiuchida.kabusapi.client_ex.pushapi.BoardBeanUtil;

@ServerEndpoint("/websocket")
public class MainWSServer {
	/**
	 * WebSocketセッション。
	 */
	private static Session mainSession;

	/**
	 * メッセージ送信スレッド。
	 */
	private static Thread senderThread;

	public MainWSServer() {
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig ec) {
		System.out.println("onOpen: " + session.toString());
		System.out.println("onOpen: " + ec.toString());
		System.out.flush();
		if (initSenderThread(session)) {
			System.out.println("onOpen: senderThread start");
		}
	}

	@OnMessage
	public void onMessage(String message) {
		try {
			System.out.println("onMessge: " + message);
			System.out.flush();
		} catch (Throwable t) {
			t.printStackTrace(System.out);
			System.out.flush();
		}
	}

	@OnError
	public void onError(Throwable th) {
		System.out.println("onError： " + th.getMessage());
		th.printStackTrace(System.out);
		System.out.flush();
	}

	@OnClose
	public void onClose(Session session, CloseReason reason) {
		System.out.println("onClose: " + session.toString());
		System.out.flush();
	}

	private synchronized boolean initSenderThread(Session session) {
		if (mainSession == null) {
			mainSession = session;
			senderThread = new Thread() {
				private double price = 1.0;
				public void run() {
					while (true) {
						BoardBean bb = createTestData(price++);
						String msg = BoardBeanUtil.toJsonString(bb);
						Set<Session> sessions = mainSession.getOpenSessions();
						for (Session s : sessions) {
							try {
								s.getBasicRemote().sendText(msg);
							} catch (IOException e) {
								e.printStackTrace(System.out);
							}
						}
						System.out.println("sendText " + sessions.size() + " clients: " + msg);
						try {
							Thread.sleep(10 * 1000);
						} catch (InterruptedException e) {
							e.printStackTrace(System.out);
						}
					}
				}
			};
			senderThread.start();
			return true;
		}
		return false;
	}

	private BoardBean createTestData(double price) {
		BoardBean bb = new BoardBean();
		bb.symbol = "9999";
		bb.symbolName = "Dummy";
		bb.currentPrice = price;
		bb.currentPriceTime = OffsetDateTime.now();
		return bb;
	}

}
