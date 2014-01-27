require 'test_helper'

class Asserts < TestCase
  def setup
    @issues_dir = File.join(PROJECT_ROOT, "issues")
    @iterator = BattleAsserts::IssueIterator.new(@issues_dir)
  end

  def test_allowed_keys
    allowed_keys = ["level", "tags", "description", "checks", "author"]
    required_keys = ["level", "checks"]
    allowed_lang_keys = ["setup", "asserts"]
    required_lang_keys = ["asserts"]

    allowed_levels = ["elementary", "easy", "medium", "hard"]

    allowed_author_keys = ["github_nickname", "web_page"]
    required_author_keys = ["github_nickname"]

    @iterator.each do |name, issue|
      puts "issue: #{name}"
      required_keys.each do |key|
        assert { issue.has_key? key }
      end
      assert { (issue.keys - allowed_keys).empty? }

      assert { allowed_levels.include? issue["level"] }

      issue["checks"].each do |lang, params|
        assert { params.is_a? Hash }
        required_lang_keys.each do |key|
          assert { params.has_key? key }
        end
        assert { (params.keys - allowed_lang_keys).empty? }

        assert { params["asserts"].is_a? Array }
      end

      author_info = issue["author"]
      if author_info
        assert { (author_info.keys - allowed_author_keys).empty? }
        required_author_keys.each do |key|
          assert { author_info.has_key?(key) }
        end
      end
    end
  end

  def test_syntax
    m = BattleAsserts::SyntaxChecker
    @iterator.each do |name, issue|
      puts "issue: #{name}"
      issue["checks"].each do |lang, code|
        next if lang != "ruby"
        all_code = "#{code["setup"]}\n\n#{code["asserts"].join("\n")}"
        m.send(lang, all_code) do |out|
          assert_equal "Syntax OK\n", out, "issue: #{name}"
        end
      end
    end
  end
end
