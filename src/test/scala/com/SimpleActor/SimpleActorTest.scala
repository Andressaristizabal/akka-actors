package com.SimpleActor

import akka.actor.{ActorSystem, Props}
import akka.testkit.{TestKit, TestProbe}
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, Matchers}

import scala.concurrent.duration._

class IntoActorTest(_system: ActorSystem) extends TestKit(_system) with Matchers with FlatSpecLike with BeforeAndAfterAll {
  def this() = this(ActorSystem("ActorSystemPrueba"))

  override def afterAll(): Unit = {
    shutdown(system)
  }

  "Un Actor" should
    "recibir mensajes y responder si es el caso" in {
      //createmos el actor
      val testProbe = TestProbe()
      val simpleActor = system.actorOf(Props(new SimpleActor(testProbe.ref)))
      simpleActor ! "hello"
      testProbe.expectMsg(500 millis, "que mas pues")
      simpleActor ! "goodbye"
      testProbe.expectMsg(500 millis, "que te choco?")
      testProbe.expectNoMsg(500 millis)
    }

  "Un Actor" should
    "puede cambiar su comportamiento ante los mensajes" in {
    //create test actor
    val testProbe = TestProbe()
    val changeActor = system.actorOf(Props(new ChangeBehaviorActor(testProbe.ref)))
    changeActor ! "hello"
    testProbe.expectMsg(500 millis, "how are you?")
    changeActor ! "goodbye"
    testProbe.expectMsg(500 millis, "bye bye")
    changeActor ! "change"
    testProbe.expectMsg(500 millis, "I change a bad person")
    changeActor ! "hello"
    testProbe.expectMsg(500 millis, "Who you need?")
    changeActor ! "goodbye"
    testProbe.expectMsg(500 millis, "bye")
    changeActor ! "change"
    testProbe.expectMsg(500 millis, "I change a nice person")
  }

  "Un Actor" should
    "enviar mensajes de dos maneras con ! 'tell' o ? 'forward' " in {

  }
}
