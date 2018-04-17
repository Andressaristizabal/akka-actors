package com.SimpleActor

import akka.actor.{Actor, ActorLogging, ActorRef}

case class ChangeBehaviorActor(actor: ActorRef) extends Actor with ActorLogging{
  override def receive = nicePerson

  def nicePerson: Receive = {
    case "hello" => actor ! "how are you?"
    case "goodbye" => actor ! "bye bye"
    case _ =>
      actor ! "I change a bad person"
      context.become(badPerson)
  }

  def badPerson: Receive = {
    case "hello" => actor ! "Who you need?"
    case "goodbye" => actor ! "bye"
    case _ =>
      actor ! "I change a nice person"
      context.become(nicePerson)
  }
}
