package lou.alex.workflow

/**
  * Created by Alex on 2/16/2017.
  */
trait StatusMatcher[S, E, F] {
  def gets(event:EventMatcher[E]) : ValidationContext[F]
}

case class StatusTypeMatcher[S, E, F](status:S, statusType:Class[_ >: S]) extends StatusMatcher[S, E, F] {
  override def gets(event: EventMatcher[E]): ValidationContext[F] = ???
}