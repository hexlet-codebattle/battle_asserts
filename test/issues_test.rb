require 'test_helper'

class Asserts < TestCase
  def setup
    @issues_dir = File.join(PROJECT_ROOT, "issues")
    @iterator = IssueIterator.new(@issues_dir)
    @allowed_keys = ["level", "tags", "description", "checks"]
  end

  def test_allowed_keys
    @iterator.each do |issue|
      @allowed_keys.each do |key|
        assert { issue.has_key? key }
      end
      assert { (issue.keys - @allowed_keys - ["name"]).empty? }
    end
  end
end
