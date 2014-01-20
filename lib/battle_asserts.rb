require 'yaml'

module BattleAsserts
  autoload :IssueIterator, 'battle_asserts/issue_iterator'
  autoload :SyntaxChecker, 'battle_asserts/syntax_checker'

  def self.issues
    IssueIterator.new File.join(File.dirname(__FILE__), '..', 'issues')
  end
end
