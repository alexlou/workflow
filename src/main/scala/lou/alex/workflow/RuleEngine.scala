package lou.alex.workflow



trait MatchResult[T] {
  def v:T
  def accept[T2](another :MatchResult[T2]) : MatchResult[T2]
}
case class Matched[T](v:T) extends MatchResult[T] {
  override def accept[T2](another: MatchResult[T2]): MatchResult[T2] = another
}
case class MisMatched[T](v:T) extends MatchResult[T] {
  override def accept[T2](another: MatchResult[T2]): MatchResult[T2] = another match {
    case Matched(newVal) => MisMatched(newVal)
    case MisMatched(_) => another
  }
}

case class RuleContext1[T](t:T, current:MatchResult[T]) {
  protected def matches[T1](matchResult: MatchResult[T1]) : RuleContext2[T, T1] = RuleContext2(t, matchResult.v, current.accept(matchResult))
  def in[T1](matchResult: MatchResult[T1]) : RuleContext2[T, T1] = matches(matchResult)
}

case class RuleContext2[T1, T2](t1:T1, t2:T2, current:MatchResult[T2]) {
  protected def matches[T3](matchResult: MatchResult[T3]) : RuleContext3[T1, T2, T3] =
    RuleContext3(t1, t2, matchResult.v, current.accept(matchResult))
  def gets[T3](matchResult: MatchResult[T3]) : RuleContext3[T1, T2, T3] = matches(matchResult)
}

case class RuleContext3[T1, T2, T3](t1:T1, t2:T2, t3:T3, current:MatchResult[T3]) {
  def validate() : Validator[T1, T2, T3] =
}


trait ValidationContext[A] {
  def validate(additional:A)
}

class Validator[T, E, A](entity:T, event:E, additional:A){
  def by(rules: ((T, E, A) => ValidationResult) *): ValidationResult = {
    rules.foldLeft[ValidationResult](ValidationSuccess)((vr, rule) => vr.accept(rule(entity, event, additional)))
  }
}

trait ValidationResult {
  def accept(validationResult: ValidationResult): ValidationResult
}
object ValidationSuccess extends ValidationResult {
  override def accept(validationResult: ValidationResult): ValidationResult = validationResult match {
    case ValidationSuccess => ValidationSuccess
    case ValidationFailure(_) => validationResult
  }
}
case class ValidationFailure(reasons: List[String]) extends ValidationResult {
  override def accept(validationResult: ValidationResult): ValidationResult = validationResult match {
    case ValidationSuccess => this
    case ValidationFailure(newReasons:List[String]) => ValidationFailure(reasons:::newReasons)
  }
}