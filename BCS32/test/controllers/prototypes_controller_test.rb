require 'test_helper'

class PrototypesControllerTest < ActionDispatch::IntegrationTest
  test "should get about" do
    get prototypes_about_url
    assert_response :success
  end

  test "should get auction" do
    get prototypes_auction_url
    assert_response :success
  end

  test "should get barter" do
    get prototypes_barter_url
    assert_response :success
  end

end
