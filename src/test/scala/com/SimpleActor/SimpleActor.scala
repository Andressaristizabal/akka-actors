package com.SimpleActor

import akka.actor.{Actor, ActorLogging, ActorRef}

/**
 * @param actor hace referencia a un actor, el cual se utilizara para responder */

//Para crear un actor solo se debe extender del trait Actor
//y sobreescribir el metodo receive
case class SimpleActor(actor: ActorRef) extends Actor with ActorLogging{
  //El metodo receive, define el comportamiento (logica que se debe ejecutar)
  //por cada mensaje que el ingresa a el MainBox del actor
  override def receive = {
    case "hello" => actor ! "que mas pues"
    case "goodbye" => actor ! "que te choco?"
    case _ => Unit
  }
}
