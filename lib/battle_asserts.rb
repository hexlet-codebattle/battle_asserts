require 'yaml'

module BattleAsserts
  autoload :IssueIterator, 'battle_asserts/issue_iterator'

  def self.issues
    IssueIterator.new File.join(File.dirname(__FILE__), '..', 'issues')
  end
end
